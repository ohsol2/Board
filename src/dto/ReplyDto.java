package dto;

public class ReplyDto {
	private int boardbno;
	private int bno;
	private String id;
	private String recontent;
	private String writedate;
	private int rebno;
	
	public ReplyDto(int boardbno, int bno, String id, String recontent, String writedate, int rebno) {
		this.boardbno = boardbno;
		this.bno = bno;
		this.id = id;
		this.recontent = recontent;
		this.writedate = writedate;
		this.rebno = rebno;
	}

	public int getBoardbno() {
		return boardbno;
	}

	public void setBoardbno(int boardbno) {
		this.boardbno = boardbno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecontent() {
		return recontent;
	}

	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public int getRebno() {
		return rebno;
	}

	public void setRebno(int rebno) {
		this.rebno = rebno;
	}
	
	
	
}
