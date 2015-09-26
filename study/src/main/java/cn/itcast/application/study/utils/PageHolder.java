package cn.itcast.application.study.utils;

public class PageHolder {
	
	private static ThreadLocal<Page> pageMap = new ThreadLocal<Page>() ;

	public static Page getPage() {
		return pageMap.get();
	}

	public static void setPage(Page page) {
		pageMap.set(page);
	}
	
	

}
