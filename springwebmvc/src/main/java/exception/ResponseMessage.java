package exception;

public class ResponseMessage {
	
	private Integer statusCode;
	private String statusName;
	private String statusMessage;
	
	public ResponseMessage() {}
	
	public ResponseMessage(Integer statusCode, String statusName, String statusMessage) {
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.statusMessage = statusMessage;
	}

	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
}
