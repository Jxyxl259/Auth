package com.yaic.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Serializable {

	private static final long serialVersionUID = -7651504159637327917L;

	public static String ROOTNODE = "root";

	public static String ROOTRESOURCEID = "0000";

	/**
	 * 节点Id
	 */
	private String id;

	/**
	 * 层级
	 */
	private String level;

	/**
	 * 父节点Id
	 */
	private String parentId;

	/**
	 * 样式
	 */
	private String icon;

	/**
	 * 结束标志
	 */
	private String endFlag;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 执行路径
	 */
	private String actionUrl;

	/** 机构是否有效标志 */
	private String validFlag;

	/** 机构备注 */
	private String remark;

	/** 机构其他标志 */
	private String flag;

	/** 机构中文名称 */
	private String companyCname;

	/** 机构英文名称 */
	private String companyEname;

	/** 机构繁体名称 */
	private String companyTname;

	/** 显示顺序 */
	private Integer displayOrder;

	public String getCompanyCname() {
		return companyCname;
	}

	public void setCompanyCname(String companyCname) {
		this.companyCname = companyCname;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	private boolean selected;

	private boolean opened;

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		if (state == null) {
			state = new State();
		}
		this.state.setOpened(opened);
	}

	public String getCompanyEname() {
		return companyEname;
	}

	public void setCompanyEname(String companyEname) {
		this.companyEname = companyEname;
	}

	public String getCompanyTname() {
		return companyTname;
	}

	public void setCompanyTname(String companyTname) {
		this.companyTname = companyTname;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		if (state == null) {
			state = new State();
		}
		this.state.setSelected(selected);
	}

	/**
	 * 节点状态
	 */
	private State state;

	/**
	 * 节点名称
	 */
	private String text;

	/**
	 * 子节点
	 */
	private List<TreeNode> children;

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public void addChild(TreeNode child) {
		if (children == null) {
			children = new ArrayList<TreeNode>();
		}
		this.children.add(child);
	}

	class State {

		private boolean opened;

		private boolean disabled;

		private boolean selected;

		public boolean isOpened() {
			return opened;
		}

		public void setOpened(boolean opened) {
			this.opened = opened;
		}

		public boolean isDisabled() {
			return disabled;
		}

		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
