package page;

public class PageDTO {
	String pageLogo;
	String pageImage;
	public String getPageLogo() {
		return pageLogo;
	}
	public void setPageLogo(String pageLogo) {
		this.pageLogo = pageLogo;
	}
	public String getPageImage() {
		return pageImage;
	}
	public void setPageImage(String pageImage) {
		this.pageImage = pageImage;
	}
	
	public PageDTO() {
	}
	
	public PageDTO(String pageLogo, String pageImage) {
		super();
		this.pageLogo = pageLogo;
		this.pageImage = pageImage;
	}
}
