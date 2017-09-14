package com.mengoud.babynametor.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BabyNameModel {
	private String firstName;
	private boolean babyMale;
	private int nameStyle;
	//当前页码
	private int currentPage = 1;
	
	/**
	 * 男性名字池
	 */
	private static final List<String> MALE_NAMES = new ArrayList<>();
	/**
	 * 女性名字池
	 */
	private static final List<String> FEMALE_NAMES = new ArrayList<>();

	static {
		// 加载名字池
		try {
		loadNamePool();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载名字池
	 */
	private static void loadNamePool() throws IOException {
		InputStream in = BabyNameModel.class.getResourceAsStream("/assets/data/男性");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));) {

			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.length() != 0) {
					MALE_NAMES.addAll(Arrays.asList(line.split("\\s+")));
				}
			}
		}
		in = BabyNameModel.class.getResourceAsStream("/assets/data/女性");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));) {

			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.length() != 0) {
					FEMALE_NAMES.addAll(Arrays.asList(line.split("\\s+")));
				}
			}
		}
	}

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isBabyMale() {
		return babyMale;
	}

	public void setBabyMale(boolean babyMale) {
		this.babyMale = babyMale;
	}

	public int getNameStyle() {
		return nameStyle;
	}

	public void setNameStyle(int nameStyle) {
		this.nameStyle = nameStyle;
	}

	/**
	 * 推荐宝宝的名字
	 * 
	 * @return
	 */
	public String[] commendNames() {
		this.currentPage=1;
		return commentNames(1);
	}
	private String[] commentNames(int pageNo) {
		List<String> names = null;
		if (babyMale) {
			names = MALE_NAMES;
		} else {
			names = FEMALE_NAMES;
		}
		String[] result = new String[5];
		String name = null;
		for (int i =(pageNo-1)*5,j=0; i<pageNo*5;i++) {
			// 3根据名字形式处理
			name = names.get(i);
			switch (this.nameStyle) {
			case 1:// 单字
				name = name.substring(0, 1);
				break;
			case 2:// 双字
				break;
			case 3:// 叠字
				name = name.substring(0, 1);
				name += name;
				break;
			}
			// 4每个名字加上姓氏
			result[j++] = this.firstName + name;
		}
		return result;
	}
	/**
	 * 获取推荐名字的下一批
	 * 
	 * @return
	 */
	public String[] nextCommendNames() {
		return commentNames(++this.currentPage);
	}

	/**
	 * 获取推荐名字的上一批
	 * 
	 * @return
	 */
	public String[] preCommendNames() {
		return commentNames(--this.currentPage);
	}

	@Override
	public String toString() {
		return "BabyNameModel [firstName=" + firstName + ", babyMale=" + babyMale + ", nameStyle=" + nameStyle
				+ ", currentPage=" + currentPage + "]";
	}

}
