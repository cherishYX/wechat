package com.web.yx.spliter;

/**
 * 解析网页中http连接
 * @author YanXiang
 *
 */
public class ParseHttpUrlAddressUtil {
	/*public static void main(String[] args) {
	try {
			Set<String> emails = new ParseHttpUrlAddress("http://www.baidu.com/").call();
			for(String e:emails){
				System.out.println(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class ParseHttpUrlAddress implements Callable<Set<String>>{
	private Log log = LogFactory.getLog(ParseHttpUrlAddress.class);
	
	private String viaUrl;
	//private final String HTTP_URL_PATTERN = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
	
	private HttpClientPool pool = HttpClientPool.getInstance();
	public ParseHttpUrlAddress(String viaUrl){
		this.viaUrl = viaUrl;
	}
	
	public Set<String> call() {
		Set<String> urls = new HashSet<String>();
		String charset = NetUtil.getNetCharset(viaUrl);
		InputStream in = null;
		BufferedReader br = null;
		try{
			HttpGet httpGet = new HttpGet(viaUrl);
			HttpResponse response = pool.execute(httpGet);
			Parser parser = Parser.createParser(EntityUtils.toString(response.getEntity()), charset);
			NodeClassFilter linkFilter =new NodeClassFilter(LinkTag.class);
			NodeList linkList = parser.extractAllNodesThatMatch(linkFilter);
			int len = linkList.size();
			for(int i = 0;i<len;i++){
				Node node = linkList.elementAt(i);
				if (node instanceof LinkTag){
					LinkTag link = (LinkTag) node;
					System.out.println(link.getLinkText() + ":" + link.getLink());
				}
			}
			
		}catch(Exception e){
			log.error("ParseHttpUrlAddress.call:",e);
		}finally{
			try {
				if(br!=null){
					br.close();
				}
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				log.error(e);
			}
		}
		return urls;
	}*/
	
	/*public Set<String> call() {
		Set<String> urls = new HashSet<String>();
		String charset = NetUtil.getNetCharset(viaUrl);
		InputStream in = null;
		BufferedReader br = null;
		try{
			URL url = new URL(viaUrl);
			in = url.openStream();
			br = new BufferedReader(new InputStreamReader(in,charset));
			String line = null;
			Pattern pattern = Pattern.compile(HTTP_URL_PATTERN);
			while((line = br.readLine()) != null){
				//System.out.println(line);
				Matcher matcher = pattern.matcher(line);
				while(matcher.find()){
					System.out.println("========");
					urls.add(matcher.group());
				}
			}
		}catch(Exception e){
			log.error("ParseHttpUrlAddress.call:",e);
		}finally{
			try {
				if(br!=null){
					br.close();
				}
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				log.error(e);
			}
		}
		return urls;
	}*/
	
}
