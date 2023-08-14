package action;


public class ActionFactory {
	private static ActionFactory instance = new ActionFactory(); //2. 스테틱!!!
	private ActionFactory() {}  //싱클톤!!!!!!!!!!!
	public static ActionFactory getInstance() { //스테틱!!! -> 1. 인스턴스변수 접근 못함
		return instance;
	}
	public Action getAction(String command) {
		Action action = null;
		switch(command) {
		case "board_list" : action = new BoardListAction(); break;
		case "board_content" : action = new BoardContentAction(); break;
		case "Log_in" : action = new LoginAction(); break;
		case "Log_out" : action = new LogoutAction(); break;
		case "Sign_up" : action = new SignupAction(); break;
		case "write" : action = new BoardWriteAction(); break;
		case "my_page" : action = new MyPageAction(); break;
		case "board_update" : action = new BoardUpdateAction(); break;
		case "board_delete" : action = new BoardDeleteAction(); break;
		case "view_update" : action = new ViewsUpdateAction(); break;
		case "like" : action = new LikeUpdateAction(); break;
		case "dislike" : action = new DisLikeUpdateAction(); break;
		case "reply_plus" : action = new ReplyInsertAction(); break;
		case "re_reply_plus" : action = new ReReplyInsertAction(); break;
		}
		return action;
	}
}
