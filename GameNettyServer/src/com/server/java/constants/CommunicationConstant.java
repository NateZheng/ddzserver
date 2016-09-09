package com.server.java.constants;
/**
 * REQ 请求
 * REP 返回
 * BDC 广播
 * @author nate
 *
 */
public class CommunicationConstant {
	/**
	 * 进入大厅
	 * 退出大厅
	 * 进入游戏
	 * 退出游戏
	 */
	public static short REQ_ENTER_HALL = 100;
	public static short REQ_EXIT_HALL = 101;
	/**
	 * 所有动作失败的消息封装
	 */
	public static short REP_ACTION_FALSE = 99;


	/**
	 * 房间动作
	 */
	public static short REQ_CREATE_ROOM = 200;//创建组局
	public static short REP_CREATE_ROOM_TURE = 201;//创建组局成功
	public static short REQ_JOIN_ROOM = 202;//加入组局,需要考虑创建者和非创建者
	public static short REP_JOIN_ROOM_TURE = 203;//加入组局成功
	public static short BDC_WHO_IN_ROOM = 204;//广播谁加入组局
	public static short REQ_DISMISS_ROOM = 205;//解散组局
	public static short BDC_WANT_DISMISS_ROOM = 206;//广播组局解散请求
	public static short BDC_DISMISS_ROOM_RESULT = 207;//广播组局解散结果
	public static short REQ_DISMISS_ROOM_REFUSE = 208;//拒绝解散房间
	public static short REQ_DISMISS_ROOM_AGREE = 209;//同意解散房间
	public static short REQ_OUT_ROOM = 210;//离开房间（房间保留）,需要考虑创建者和非创建者
	public static short BDC_WHO_OUT = 211;//广播谁离开房间
	public static short BDC_ROOM_STATISTICS = 212;//广播牌组局总得分

	public static short REQ_JOININ_GAME = 213;//请求进入大厅游戏
	public static short REP_JOININ_GAME_TURE = 214;//回复进入游戏成功
	public static short BDC_WHO_IN_GAME = 215;//广播谁加入游戏

	public static short REQ_JOININ_MATCH = 216;//请求进入比赛

	/**
	 * 
	 */
	public static short REQ_PLAYER_READY = 301;//已准备
	public static short BDC_PLAYER_READY = 302;//广播有玩家已准备
	public static short BDC_ROUND_START = 303;//广播牌局开始
	public static short BDC_PLAYER_CARDS = 304;//服务器给玩家发牌

	public static short BDC_CALL_FIGHT = 305;//广播叫地主-叫抢
	public static short REQ_IS_CALL_DIZHU = 306;//玩家是否叫or抢地主
	
	public static short BDC_CALL_POINT = 307;//广播叫地主-叫分
	public static short REQ_DIZHU_POINTS = 308;//玩家叫分
	
	public static short REQ_PUSH_CARD = 309;//玩家出牌
	public static short BDC_WHO_PUSH = 310;//广播谁出牌

	public static short REQ_PASS_CARD = 311;//玩家过牌
	public static short BDC_WHO_PASS = 312;//广播谁过牌

	public static short REQ_IS_AUTO_PLAY = 313;//玩家是否托管
	public static short BDC_WHO_AUTO_PLAY = 314;//广播谁托管

	public static short BDC_ROUND_END = 315;//广播牌局结束
	public static short BDC_ROUND_STATISTICS = 316;//广播牌局得分
	
	public static short REQ_EXIT_ROUND = 317;//玩家请求退出牌局
	public static short REP_EXIT_ROUND_TURE = 318;//回复退出牌局成功
	public static short BDC_WHO_EXIT_ROUND = 319;//广播谁退出牌局
	
	
}
