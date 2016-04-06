package com.web.yx.model.vo;

public class Music {
//     <Music>
//	 <Title><![CDATA[TITLE]]></Title>
//	 <Description><![CDATA[DESCRIPTION]]></Description>
//	 <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
//	 <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
//	 </Music>
	
	private String title;
	
	private String description;
	
	/**
	 * 音乐链接 
	 */
	private String musicUrl ;
	
	/**
	 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐 
	 */
	private String hQMusicUrl; 	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return hQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}

	@Override
	public String toString() {
		return "Music [title=" + title + ", description=" + description
				+ ", musicUrl=" + musicUrl + ", hQMusicUrl=" + hQMusicUrl + "]";
	}
	
}
