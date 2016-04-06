package com.web.yx.model;

import java.util.Iterator;
import java.util.Map;

import com.web.yx.model.vo.Article;
import com.web.yx.model.vo.Music;
/**
 * 返回参数
 * @author Administrator
 *
 */
public class ResponseBean {

public static final String TEXT_MSG_TYPE="text";
	
	public static final String MUSIC_MSG_TYPE="music";
	
	public static final String NEW_MSG_TYPE="news";
	 
	
	/**
	 * 接收方帐号（收到的OpenID） 
	 */
	private String toUserName;
	
	/**
	 * 开发者微信号 
	 */
	private String fromUserName;
	
	/**
	 * 消息创建时间 
	 */
	private Long createTime=Long.valueOf(System.currentTimeMillis());
	
	/**
	 * 回复消息类型
	 * text 、music、news
	 */
	private String msgType;
	
	/**
	 * 
	 * 回复的消息内容,长度不超过2048字节 
	 */
	private String content;
	
	/**
	 * 位0x0001被标志时，星标刚收到的消息。
	 */
	private Integer funcFlag=Integer.valueOf(0);
	
	/**
	 * 回复音乐消息
	 */
	private Music music;
	/**
	 * 回复图文消息
	 */
	private Article article;

	public String getToUserName() {
		return toUserName;
	}
	/**
	 * 设置接收方账号
	 * @param toUserName
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}
	/**
	 * 设置开发者微信号
	 * @param fromUserName
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(Integer funcFlag) {
		this.funcFlag = funcFlag;
	}


	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
	
	/**
	 * bean to xml
	 * @return
	 */
	public String toXML(){
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		if(toUserName!=null&&!"".equals(toUserName)){
			sb.append("<ToUserName><![CDATA[").append(toUserName).append("]]></ToUserName>");
		}else{
			throw new IllegalArgumentException("toUserName must be not null");
		}
		if(fromUserName!=null&&!"".equals(fromUserName)){
			sb.append("<FromUserName><![CDATA[").append(fromUserName).append("]]></FromUserName>");
		}else{
			throw new IllegalArgumentException("fromUserName must be not null");
		}
		sb.append("<CreateTime>").append(createTime).append("</CreateTime>");
		if(msgType!=null && !"".equals(msgType)){
			sb.append("<MsgType><![CDATA[").append(msgType).append("]]></MsgType>");
			if("text".equals(msgType)){
				sb.append("<Content><![CDATA[").append(content).append("]]></Content>");
			}else if("music".equals(msgType)){
				sb.append("<Music>");
				String title = music.getTitle();
				String description = music.getDescription();
				String musicUrl= music.getMusicUrl();
				String hQMusicUrl =music.getHQMusicUrl();
				if(title!=null&&!"".equals(title)){
					sb.append("<Title><![CDATA[").append(title).append("]]></Title>");
				}
				if(description!=null&&!"".equals(description)){
					sb.append("<Description><![CDATA[").append(description).append("]]></Description>");
				}
				if(musicUrl!=null&&!"".equals(musicUrl)){
					sb.append("<MusicUrl><![CDATA[").append(musicUrl).append("]]></MusicUrl>");
				}else{
					throw new IllegalArgumentException("MusicUrl must be not null");
				}
				if(hQMusicUrl!=null&&!"".equals(hQMusicUrl)){
					sb.append("<HQMusicUrl><![CDATA[").append(hQMusicUrl).append("]]></HQMusicUrl>");
				}
				sb.append("</Music>");
			}else if("news".equals(msgType)){
				if(article.getArticleCount()==0){
					throw new IllegalArgumentException("ArticleCount must above 0");
				}
				if(article.getArticleCount()!=article.getItems().size()){
					throw new IllegalArgumentException("ArticleCount must be equals article's item count");
				}
				sb.append("<ArticleCount>").append(article.getArticleCount()).append("</ArticleCount>");
				sb.append("<Articles>");
				for(Iterator<Map<String,String>> i=article.getItems().iterator();i.hasNext();){
					sb.append("<item>");
					Map<String,String> item  = i.next();
					String title = item.get("Title");
					String description = item.get("Description");
					String picUrl = item.get("PicUrl");
					String url = item.get("Url");
					if(title!=null&&!"".equals(title)){
						sb.append("<Title><![CDATA[").append(title).append("]]></Title>");
					}
					if(description!=null&&!"".equals(description)){
						sb.append("<Description><![CDATA[").append(description).append("]]></Description>");
					}
					if(picUrl!=null&&!"".equals(picUrl)){
						sb.append("<PicUrl><![CDATA[").append(picUrl).append("]]></PicUrl>");
					}
					if(url!=null&&!"".equals(url)){
						sb.append("<Url><![CDATA[").append(url).append("]]></Url>");
					}
					sb.append("</item>");
				}
				sb.append("</Articles>");
			}else{
				throw new IllegalArgumentException("msgType must be one of text、music、news");
			}
			
		}else{
			throw new IllegalArgumentException("msgType must be not null");
		}
		sb.append("<FuncFlag>").append(funcFlag).append("</FuncFlag>");
		sb.append("</xml>");
		return sb.toString();
	}
	@Override
	public String toString() {
		return "ResponseBean [toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + ", content=" + content + ", funcFlag=" + funcFlag
				+ ", music=" + music + ", article=" + article + "]";
	}
	
	

}
