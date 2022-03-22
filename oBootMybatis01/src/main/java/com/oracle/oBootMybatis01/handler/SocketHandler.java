package com.oracle.oBootMybatis01.handler;

import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//컴포넌트 - 스프링 컨테이너에 자동으로 안착시킴 - 메모리에 올려두는작업
@Component
public class SocketHandler extends TextWebSocketHandler {//상속받을시 아래 3가지 메소드 기본으로가져옴
	//웹소켓 세션을 담아둘 맵 - jsp의 컨트롤러에 서비스를 맵형식으로 저장한거랑 비슷한개념
	//저장할 객체를만들때 앞에있는 값이랑 뒤에값이다를려면 상속받은 클래스여야함? ex - > list 뭐시기 = new arraylist 가능 arraylist가 자식이라서 가능한거임
	//김준수 , 3109c628-3185-4dd6-173e-01f5a981c357 인스턴스 
	//조정훈 , 3109c628-3185-4dd6-173e-01f5a981c357 - 인스턴스
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	// 웹소켓 세션 ID와 Member를 담아둘 맵
	HashMap<String, String> sessionUserMap = new HashMap<>();
	// 웹소켓 세션 ID과 Member을 담아둘 JSONObject타입의 jsonuser
	JSONObject jsonUser = null;
	
							//세션값을 가져오기위해 매개벼수에 선언?
	// 이 메소드는 메시지를 수신하면 실행 메세지수신위해 textmessage 가져옴
	public void handleTextMessage(WebSocketSession session , TextMessage message) {//여기 텍스트메세지가있어서 뷰딴에서보낸 메세지를 받을수있음
		//메세지발송
		String msg = message.getPayload(); //뷰딴에서보낸 메세지 받아서 보여줌
		System.out.println("SocketHandler handleTextMessage msg->"+msg);
		
		JSONObject jsonObj = jsonToObjectParser(msg); //메세지를 json오브젝트 타입으로 파싱(감싼다? 타입을바꿈) -
		//type을 get하여 보기
		String msgType=(String) jsonObj.get("type"); //여기서 타입은 메세지임 뷰딴에서 보낼때 json형태를 스트링으로바꿔서 보낼때 키 벨류로 type의 벨류는 메세지엿음
		System.out.println("SocketHandler handleTextMessage msgType->"+msgType);
		switch(msgType) {//타입에따라서 이게 메세지냐,유저네임이냐 에서 메세지면 메세지보내기 유저네임이면 유저네임으로 접속
		//전체 Message
		case "message":
			jsonUser = new JSONObject(sessionUserMap);//sessionUserMap을 json타입으로 변환
			System.out.printf("JSONUser: %s",jsonUser);
			//전송대상을 기준으로 분기
			String yourName = (String) jsonObj.get("yourName");
			System.out.println("SocketHandler handleTextMessage yourName->"+yourName);
			//전체
			if(yourName.equals("ALL")) {//모든 접속한 유저에게 보내기
				for(String key : sessionMap.keySet()) {
					WebSocketSession wss = sessionMap.get(key);
					try {
						System.out.println("message key->"+key);
						System.out.println("message key->"+wss);
						wss.sendMessage(new TextMessage(jsonObj.toJSONString()));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}else { //개인 전송 대상자 (yourName은 개인 sessionId) 누구한테보낼것인가? 밑에 wssl에다가 상대방이름 저장 wss2에다가는 내이름 저장
				//상대방
				System.out.println("개인 전송 대상자 상대방->"+yourName);
				WebSocketSession wss1 = sessionMap.get(yourName);
				try {
					wss1.sendMessage(new TextMessage(jsonObj.toJSONString()));//상대방을정하고 보낸 메세지 를 send(뷰딴으로 다시뿌려줌)
				}catch(Exception e) {
					e.printStackTrace();
				}
				//나에게도 보내줘
				String meName = (String) jsonObj.get("sessionId");
				WebSocketSession wss2 = sessionMap.get(meName);
				System.out.println("개인 전송 대상자 나 -->"+meName);
				try {
					wss2.sendMessage(new TextMessage(jsonObj.toJSONString()));
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			break;
		//sessionUserMap에 User등록
		case "userSave":
			//sessionUserMap에 sessionId와 userName 등록
			String sessionId =(String) jsonObj.get("sessionId");
			String userName = (String) jsonObj.get("userName");
			sessionUserMap.put(sessionId, userName);
			System.out.println("==================================================");
     	    System.out.println("== sessionUserMap 저장내용 조회하여 arrayJsonUser에   ==");
     	    System.out.println("==  각각의 JSONObject jsonUser로  변환                           ==");
     	    System.out.println("== 1. type : userSave                          ==");
     	    System.out.println("== 2. sessionId : sessionUserMap.sessionId     ==");
     	    System.out.println("== 3. userName  : sessionUserMap.userName      ==");
     	    System.out.println("=================================================");
     	    JSONArray arrayJsonUser = new JSONArray();
     	    System.out.println("== 1. type: userSave");
     	    Iterator<String> mapIter = sessionUserMap.keySet().iterator();//iterator = 해쉬맵의 키값만가져오는 거 
     	    System.out.println("== 2. sessionId : sessionUserMap.sessionId    ==");
     	    System.out.println("== 3. userName  : sessionUserMap.userName     ==");
     	    while(mapIter.hasNext()) {//이터레이터로 와일문에서 키로 반복해서 키 벨류 추출
     	    	String key = mapIter.next();
     	    	String value = sessionUserMap.get(key);
     	    	System.out.println("Key : Value->"+key+":"+value);
     	    	jsonUser = new JSONObject();
     	    	jsonUser.put("type", "userSave");
     	    	jsonUser.put("sessionId", key);
     	    	jsonUser.put("userName", value);
     	    	arrayJsonUser.add(jsonUser);
     	    }
     	    System.out.println("===== session를 Get하여 User 내용 전송=====");
     	    System.out.printf("arrayJsonUser: %s",arrayJsonUser);
     	    
     	    //전체에 User등록을 하게함
     	    for(String key : sessionMap.keySet()) {
     	    	WebSocketSession wss = sessionMap.get(key);
     	    	try {
     	    		wss.sendMessage(new TextMessage(arrayJsonUser.toJSONString()));
     	    	}catch(Exception e) {
     	    		e.printStackTrace();
     	    	}
     	    }
     	    break;
		case "userDelete":
			System.out.println("handleTextMessage userDelete start...");
			System.out.println("handleTextMessage userDelete session.getId()->"+session.getId());
			//웹소켓 종료
			sessionMap.remove(session.getId());
			//sessionUserMap 종료
			sessionUserMap.remove(session.getId());
			
			break;
		}// switch End
	}
	//  @SuppressWarning
	//  이건 컴파일러가 일반적으로 경고하는 내용 중	"이건 하지마"하고 제외시킬 때 쓰임
	//	따라서 어떤 경고를 제외시킬지 옵션
	//	1. all : 모든 경고를 억제
	//	2. cast : 캐스트 연산자 관련 경고 억제
	//	3. dep-ann : 사용하지 말아야 할 주석 관련 경고 억제
	//	4. deprecation : 사용하지 말아야 할 메소드 관련 경고 억제
	//	5. fallthrough : switch문에서의 break 누락 관련 경고 억제
	//	6. finally : 반환하지 않는 finally 블럭 관련 경고 억제
	//	7. null : null 분석 관련 경고 억제
	//	8. rawtypes : 제네릭을 사용하는 클래스 매개 변수가 불특정일 때의 경고 억제
	//	9. unchecked : 검증되지 않은 연산자 관련 경고 억제
	//	10. unused : 사용하지 않는 코드 관련 경고 억제
	//1
	// 웹소켓 연결이 되면 동작하는 메소드
	@SuppressWarnings("unchecked")
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		System.out.println("SocketHandler afterConnectionEstablished start...");
		//웹소켓 연결이 되면 동작
		super.afterConnectionEstablished(session);//세션 인스턴스 획득
		//연결 소켓을 맵에 등록
		sessionMap.put(session.getId(), session);//
		JSONObject jsonObj = new JSONObject();
		//대상 : Client
		jsonObj.put("type", "getId");
		jsonObj.put("sessionId", session.getId());
		//이걸담아서 다시 뷰딴으로 보내줌 
		//Socket Server가 클라이언트(자바스크립트)로 전송하는메소드
		session.sendMessage(new TextMessage(jsonObj.toJSONString()));//다시메세지 보냄 - 뷰딴으로 session으로넣으니까 뷰에서 success(data)-이안에담김
	}
	
	// 웹소켓이 종료되면 동작
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		System.out.println("SocketHandler afterConnectionClosed start...");
		//웹소켓종료
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}
	
	// handleTextMessage 메소드 에 JSON파일이 들어오면 파싱해주는 함수를 추가
	//게속왓다갓다해야하니까 브라우저타입 x json타입으로 서로 주고받음
	//내부적으로 계속돌아감 - private타입으로 이걸사용하기위해 WebSocketConfig 에서 설정 오토와이얼즈로
	private static JSONObject jsonToObjectParser(String jsonStr) {//json으로 파싱할려면 제이슨형태의 스트링이여야함
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) parser.parse(jsonStr);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
}
