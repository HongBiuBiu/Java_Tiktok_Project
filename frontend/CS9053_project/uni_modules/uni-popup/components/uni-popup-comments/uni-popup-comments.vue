<style lang="scss" scoped>
	.uni-popup-share {
		background-color: #181717;
		border-top-left-radius: 16rpx;
		border-top-right-radius: 16rpx;
	}
	.uni-share-title {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		align-items: center;
		justify-content: space-between;
		height: 40px;
	}
	.uni-share-title-text {
		font-size: 12px;
		color: #ffffff;
		font-weight: 500;
	}
	.uni-share-content {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		// flex-direction: row;
		flex-direction: column;
		// justify-content: center;
		// padding-top: 10px;
	}

	.uni-share-content-box {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		flex-wrap: wrap;
		width: 360px;
	}

	.uni-share-content-item {
		width: 90px;
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: column;
		justify-content: center;
		padding: 10px 0;
		align-items: center;
	}

	.uni-share-content-item:active {
		background-color: #f5f5f5;
	}

	.uni-share-image {
		width: 30px;
		height: 30px;
	}

	.uni-share-text {
		margin-top: 10px;
		font-size: 14px;
		color: #3B4144;
	}

	.uni-share-button-box {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		padding: 10px 15px;
	}

	.uni-share-button {
		flex: 1;
		border-radius: 50px;
		color: #666;
		font-size: 16px;
	}

	.uni-share-button::after {
		border-radius: 50px;
	}
	
	.icon-close-left {
		width: 20rpx;
		height: 20rpx;
		margin-left: 30rpx;
		opacity: 0;
	}
	.icon-close-right {
		width: 22rpx;
		height: 22rpx;
		margin-right: 30rpx;
	}
	
	.comment-wrapper {
		// height: 40px;
		padding: 10px 10px 10px 10px;
		display: flex;
		flex-direction: row;
		justify-content: space-around;
	}
	.commentWrapperBlack {
		background-color: #0e0d0d;
	}
	.commentWrapperWhite {
		background-color: #ffffff;
	}
	.comment-box {
		font-size: 15px;
		height: 40px;
		border-radius: 20px;
		padding: 0 18px 0 18px;
	}
	.commentBoxBlack {
		color: #FFFFFF;
		background-color: #151515;
	}
	.commentBoxWhite {
		color: #000000;
		background-color: #f2f2f5;
	}
	.comment-box-placeholder {
		font-size: 15px;
	}
	.icon-comment {
		width: 30px;
		height: 30px;
	}
	
	.all-comments {
		height: 800rpx;
		// padding: 16rpx 30rpx;
	}
	.img-face {
		width: 80rpx;
		height: 80rpx;
		border-radius: 100rpx;
	}
	.like-or-not {
		margin-top: 16px;
		width: 50rpx;
		height: 50rpx;
	}
	.comments-wrapper {
		display: flex;
		flex-direction: column;
	}
	.comments-wrapper-sub-up {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	.comments-wrapper-sub-down {
		display: flex;
		flex-direction: row;
		margin-top: 2px;
	}
	.vlogger-wrapper {
		display: flex;
		flex-direction: row;
	}
	.tag-writer {
		display: flex;
		flex-direction: column;
		justify-content: center;
		background-color: #f02a50; 
		border-radius: 3px;
		width: 30px;
		height: 16px;
		margin-left: 2px;
	}
	.writer-words {
		font-size: 26rpx;
		color: #FFFFFF;
		text-align: center;
	}
	
	.single-comment-box {
		background-color: #181717;
	}
	.single-comment-box-touched {
		background-color: #202020;
	}
	.active {
		background-color: #202020;
	}
</style>

<template>
	<view class="uni-popup-share">
		<view class="uni-share-title">
			<image src="/static/images/icon-close.png" class="icon-close-left"></image>
			<text class="uni-share-title-text">{{getGraceNumber(thisVlogTotalComentCounts)}}comment</text>
			<image src="/static/images/icon-close.png" class="icon-close-right" @click="close"></image>
		</view>
		<view class="uni-share-content">
			<!-- <view class="uni-share-content-box"> -->
			<view class="" style="height: 800rpx;"><!-- background-color: #0077AA; -->
				<scroll-view 
					class="all-comments" 
					:style="{width: screenWidth + 'px'}" 
					scroll-y="true" 
					lower-threshold="150"
					@scrolltolower="loadMore"
					:scroll-top="scrollTop">
					<view 
						v-for="(commentContent, index) in commentList" 
						:key="index" 
						:data-index="index" 
						:class="{active : index == activeIndex}"
						@touchstart="touchstartComment(index)"
						@touchend="touchendComment()"
						@longpress="deleteComment(commentContent.commentUserId, commentContent.commentId)">
						<view class="comments-wrapper" style="margin-left: 16rpx;margin-top: 16rpx;margin-right: 16rpx;">
							<view class="comments-wrapper-sub-up" >
								<view class="vlogger-wrapper">
									<image :src="commentContent.commentUserFace" class="img-face"></image>
									<view style="margin-left: 10px;width: 456rpx;">
										<view style="display: flex;flex-direction: row;">
											<text style="font-size: 30rpx;color: #878585;align-self: center;">{{commentContent.commentUserNickname}}</text>
											<view v-if="commentContent.vlogerId == thisVlogerId" class="tag-writer" style="align-self: center;">
												<text class="writer-words" style="align-self: center;">Vloger</text>
											</view>
											
											<image 
												v-if="commentContent.replyedUserNickname != null  && commentContent.replyedUserNickname != ''" 
												src="/static/images/icon-right-arrow3.png" class="" 
												style="opacity: 0.8;width: 40rpx;height: 40rpx;margin-left: 16rpx;margin-right: 10rpx;align-self: center;"></image>
											<text 
												v-if="commentContent.replyedUserNickname != null  && commentContent.replyedUserNickname != ''" 
												style="font-size: 30rpx;color: #878585;align-self: center;">{{commentContent.replyedUserNickname}}</text>
										</view>
										<text style="font-size: 32rpx;color: #FFFFFF;margin-top: 2px;">{{commentContent.content}}</text>
									</view>
								</view>
								<view style="display: flex;flex-direction: column;width: 26px;">
									<image v-if="commentContent.isLike == 1" 
										src="/static/images/icon-comment-like.png" 
										class="like-or-not" 
										style="align-self: center;"
										@click="unlike(commentContent.commentUserId, commentContent.commentId, index)"></image>
									<image v-if="commentContent.isLike == 0" 
										src="/static/images/icon-comment-unlike.png" 
										class="like-or-not" 
										style="align-self: center;"
										@click="like(commentContent.commentUserId, commentContent.commentId, index)"></image>
									<text style="font-size: 22rpx;color: #878585;align-self: center;">{{getGraceNumber(commentContent.likeCounts)}}</text>
								</view>
							</view>
							<view class="comments-wrapper-sub-down">
								<image src="/static/face/face-arrow-1.png" class="img-face" style="opacity: 0;"></image>
								<text style="font-size: 30rpx;color: #878585;margin-left: 10px;">{{getGraceDateBeforeNow(commentContent.createTime)}}</text>
								<text style="font-size: 30rpx;color: #878585;margin-left: 20px;" @click="replyComment(commentContent.commentId, commentContent.commentUserNickname)">回复</text>
							</view>
						</view>
					</view>
					
					<view style="display: flex;flex-direction: row;justify-content: center;margin-top: 50rpx;height: 100rpx;">
						<text style="color: #444446;font-size: 13px;">{{bottomTxt}}</text>
					</view>
					
				</scroll-view>

			</view>
			
			<!-- Comment input box -->
			<view class="comment-wrapper" 
				:class="{commentWrapperBlack: commentWrapperBlack, commentWrapperWhite: commentWrapperWhite}">
				<input 
					class="comment-box" 
					:class="{commentBoxBlack: commentBoxBlack, commentBoxWhite: commentBoxWhite}"
					placeholder-class="comment-box-placeholder"
					:style="{width: screenWidth-70 + 'px'}" 
					:placeholder="placeholder" 
					cursor-spacing="10px"
					v-model="currentComment"
					confirm-type="done"
					:focus="commentFocus"
					@focus="typingComment"
					@blur="noTypingComment">
					<!-- @confirm="doCommentPublish" -->
				<view style="display: flex;flex-direction: column;justify-content: center;">
					<image src="/static/images/icon-comment.png" class="icon-comment" @click="doCommentPublish()"></image>
				</view>
			</view>
					
		</view>
	</view>
</template>

<script>
	var app = getApp();
	import popup from '../uni-popup/popup.js'
	export default {
		name: 'UniPopupShare',
		mixins:[popup],
		emits:['select'],
		props: {
			title: {
				type: String,
				default: 'Share to'
			},
			beforeClose: {
				type: Boolean,
				default: false
			},
			thisVlogerId: {
				type: String,
				default: ''
			},
			thisVlogId: {
				type: String,
				default: ''
			},
		},
		data() {
			return {
				commentWrapperBlack: true,
				commentWrapperWhite: false,
				commentBoxBlack: true,
				commentBoxWhite: false,
				// commentWrapperBlack: false,
				// commentWrapperWhite: true,
				// commentBoxBlack: false,
				// commentBoxWhite: true,
				screenWidth: uni.getSystemInfoSync().screenWidth,
				currentComment: "",
				scrollTop: 0,
				old: {
					scrollTop: 0
				},
				
				// commentTouched: false,
				activeIndex: -1,
				
				bottomTxt: "End of comments, that's all~",
				placeholder: "Love comments, love life~",
				commentFocus: false,
				thisFatherCommentId: "0",	
				
				thisVlogTotalComentCounts: 0,
				loginUserId: "",
				page: 0,
				totalPage: 0,
				commentCounts: 88,
				commentList: [],
				commentList2: [
					{
						vlogerId: "10011",
						commentUserNickname: "NYU | Qingyue",
						commentUserFace: "/static/face/face-arrow-1.png",
						content: "Welcome to NYU!",
						isLike:	1,
						isOwner: 1,
						likeCounts: 6006017,
						createTime: "2022-08-18 15:20:35"
					},
					{
						vlogerId: "10012",
						commentUserNickname: "Green Arrow | Oliver Queen",
						commentUserFace: "/static/face/face-arrow-2.jpg",
						content: "Welcome，biu~biu~~",
						isLike:	1,
						isOwner: 0,
						likeCounts: 8607,
						createTime: "2022-08-20 13:20:35"
					},
					{
						vlogerId: "10013",
						commentUserNickname: "Cat",
						commentUserFace: "/static/face/face-cat.png",
						content: "Meow~~~",
						isLike:	0,
						isOwner: 0,
						likeCounts: 66017,
						createTime: "2023-05-1 20:20:20"
					}
				]
			}
		},
		created() {
			console.log("comment...created...thisVlogId = " + this.thisVlogId)
			
			var me = this;
			var myUserInfo = getApp().getUserInfoSession();
			var userId = "";
			if (myUserInfo != null) {
				userId = myUserInfo.id;
			}
			me.loginUserId = userId;
			
			this.freshCommentCounts();
			this.doCommentPagingList(this.page + 1, true);
		},
		methods: {
			freshCommentCounts() {
				var me = this;
				var vlogId = me.thisVlogId;
				
				var serverUrl = app.globalData.serverUrl;
				uni.request({
					method: "GET",
					url: serverUrl + "/comment/counts?vlogId=" + vlogId,
					success(result) {
						console.log(result);
						
						if (result.data.status == 200) {
							me.thisVlogTotalComentCounts = result.data.data;
						} else {
							me.thisVlogTotalComentCounts = 0;
						}
						
						if (me.thisVlogTotalComentCounts == 0) {
							me.bottomTxt = "First comment?";
						} else {
							me.bottomTxt = "End of comments, that's all~";
						}
						
					}
				});
			},
			
			unlike(commentUserId, commentId, index) {
				var me = this;
				
				var serverUrl = app.globalData.serverUrl;
				uni.request({
					method: "POST",
					header: {
						headerUserId: me.loginUserId,
						headerUserToken: app.getUserSessionToken()
					},
					url: serverUrl + "/comment/unlike?userId=" + me.loginUserId + "&commentId=" + commentId,
					success(result) {
						console.log(result);
						
						if (result.data.status == 200) {
							me.reLikeCommentList(index, 0);
							me.reCountsCommentList(index, -1);
						} else {
							uni.showToast({
								title: result.data.msg,
								icon: "none",
								duration: 3000
							});
						}
					}
				});
			},
			
			like(commentUserId, commentId, index) {
				var me = this;
				
				var myUserInfo = getApp().getUserInfoSession();
				if (myUserInfo == null) {
					uni.showTabBar({
						animation: false
					});
					uni.showToast({
						duration: 3000,
						title: "Please Login~",
						icon: "none"
					});
					uni.navigateTo({
						url: "../loginRegist/loginRegist",
						animationType: "slide-in-bottom",
					});
					return;
				}
				
				
				var serverUrl = app.globalData.serverUrl;
				uni.request({
					method: "POST",
					header: {
						headerUserId: me.loginUserId,
						headerUserToken: app.getUserSessionToken()
					},
					url: serverUrl + "/comment/like?userId=" + me.loginUserId + "&commentId=" + commentId,
					success(result) {
						console.log(result);
						
						if (result.data.status == 200) {
							me.reLikeCommentList(index, 1);
							me.reCountsCommentList(index, 1);
						} else {
							uni.showToast({
								title: result.data.msg,
								icon: "none",
								duration: 3000
							});
						}
					}
				});
			},
			
			reCountsCommentList(index, number) {
				var me = this;
				var commentList = me.commentList;
				
				commentList[index].likeCounts = commentList[index].likeCounts + number;
				commentList.splice(index, 1, commentList[index]);
				
				me.commentList = commentList;
			},
			
			reLikeCommentList(index, isLike) {
				var me = this;
				var commentList = me.commentList;
				
				commentList[index].isLike = isLike;
				commentList.splice(index, 1, commentList[index]);
				
				me.commentList = commentList;
			},
			
			deleteComment(commentUserId, commentId) {
				var me = this;
				var vlogId = me.thisVlogId;
				// console.log("deleteComment...deleteComment=" + commentId);
				if (commentUserId != me.loginUserId) {
					return;
				}
				
				uni.showModal({
				    title: 'Note',
				    content: 'Are you sure you want to delete this comment?',
				    success: function (res) {
				        if (res.confirm) {
							var serverUrl = app.globalData.serverUrl;
							uni.request({
								method: "DELETE",
								header: {
									headerUserId: commentUserId,
									headerUserToken: app.getUserSessionToken()
								},
								url: serverUrl + "/comment/delete?commentUserId=" + commentUserId + "&commentId=" + commentId + "&vlogId=" + vlogId,
								success(result) {
									console.log(result);
									
									if (result.data.status == 200) {
										me.doCommentPagingList(1, true);
									} else {
										uni.showToast({
											title: result.data.msg,
											icon: "none",
											duration: 3000
										});
									}
									me.freshCommentCounts();
								}
							});
				        } else if (res.cancel) {
				        }
				    }
				});
				
			},
			loadMore() {
				if (this.page == this.totalPage) {
					return;
				}
				this.doCommentPagingList(this.page + 1, false);
			},
			doCommentPagingList(page, needClearList) {
				var me = this;
				me.page = page;
				var vlogId = me.thisVlogId;
				// console.log(vlogId);
				var serverUrl = app.globalData.serverUrl;
				uni.request({
					method: "GET",
					url: serverUrl + "/comment/list?vlogId=" + vlogId + "&userId=" + me.loginUserId + "&page=" + page + "&pageSize=10",
					success(result) {
						console.log(result);
						
						if (result.data.status == 200) {
							var commentList = result.data.data.rows;
							var totalPage = result.data.data.total;
							// console.log(commentList);
							
							if (needClearList) {
								me.commentList = [];
							}
							me.commentList = me.commentList.concat(commentList);
							me.totalPage = totalPage;
							
						} else {
							uni.showToast({
								title: result.data.msg,
								icon: "none",
								duration: 3000
							});
						}
						
					}
				});
			},
			
			
			typingComment() {
				this.commentWrapperBlack = false;
				this.commentWrapperWhite = true;
				this.commentBoxBlack = false;
				this.commentBoxWhite = true;
				
				// console.log(this.thisFatherCommentId);
			},
			noTypingComment() {
				this.commentWrapperBlack = true;
				this.commentWrapperWhite = false;
				this.commentBoxBlack = true;
				this.commentBoxWhite = false;
				
				this.thisFatherCommentId = "0";		
				this.commentFocus = false;
				this.placeholder = "Love comments, love life~";
			},
			
			replyComment(commentId, commentUserNickname) {
				this.thisFatherCommentId = commentId;
				this.commentFocus = true;
				this.placeholder = "Respond @" + commentUserNickname;
				// this.typingComment();
			},
			
			doCommentPublish() {
				var me = this;
				
				var myUserInfo = getApp().getUserInfoSession();
				if (myUserInfo == null) {
					uni.showTabBar({
						animation: false
					});
					uni.showToast({
						duration: 3000,
						title: "PLease Login~",
						icon: "none"
					});
					uni.navigateTo({
						url: "../loginRegist/loginRegist",
						animationType: "slide-in-bottom",
					});
					return;
				}
				
				
				if (me.currentComment == null || me.currentComment == "" || me.currentComment == undefined) {
					uni.showToast({
						title: "please comment~",
						mask: true,
						position: "bottom"
					});
					return;
				}
				
				if (me.currentComment.length > 50) {
					uni.showToast({
						title: "The comment length should be within 50 characters~",
						mask: true,
						position: "bottom"
					});
					return;
				}
				
				
				
				var userId = myUserInfo.id;
				var serverUrl = app.globalData.serverUrl;
				
				var pendingCommentObject = {
					vlogId: me.thisVlogId,
					vlogerId: me.thisVlogerId,
					fatherCommentId: me.thisFatherCommentId,
					commentUserId: userId,
					content: me.currentComment,
				};
				
				uni.request({
					method: "POST",
					header: {
						headerUserId: userId,
						headerUserToken: app.getUserSessionToken()
					},
					url: serverUrl + "/comment/create",
					data: pendingCommentObject,
					success(result) {
						console.log(result);
						
						if (result.data.status == 200) {
							var newCommentObject = result.data.data;
							newCommentObject.commentId = newCommentObject.id;
							newCommentObject.commentUserNickname = myUserInfo.nickname;
							newCommentObject.commentUserFace = myUserInfo.face;
							newCommentObject.isLike = 0;
							// newCommentObject.isOwner = 0;
							// if (me.thisVlogerId == userId) {
							// 	newCommentObject.isOwner = 1;
							// }
							
							me.doCommentPagingList(1, true);
							
							me.thisFatherCommentId = "0";
							
							me.commentList.unshift(newCommentObject);
							me.currentComment = "";
							uni.hideKeyboard();
							me.noTypingComment();
							
							me.scrollTop = me.scrollTop + 1;
							me.$nextTick(()=>{
								me.scrollTop = 0 
							});
						} else {
							uni.showToast({
								title: result.data.msg,
								icon: "none",
								duration: 3000
							});
						}
						
						me.freshCommentCounts();
					}
				});
				
			},
			getGraceNumber(num) {
				return getApp().graceNumber(num);
			},
			getGraceDateBeforeNow(dateTimeStr) {
				var date = app.dateFormat("YYYY-mm-dd", new Date(dateTimeStr));
				// console.log(date);
				return getApp().getDateBeforeNow(date);
			},
			
			
			select(item, index) {
				this.$emit('select', {
					item,
					index
				})
				this.close()
				
			},
			close() {
				if(this.beforeClose) return
				this.popup.close();
				uni.showTabBar({
					animation: true
				});
			},
			
			touchstartComment(index) {
				// this.commentTouched = true;
				this.activeIndex = index;
			},
			touchendComment() {
				// this.commentTouched = false;
				this.activeIndex = -1;
			},
		}
	}
</script>
