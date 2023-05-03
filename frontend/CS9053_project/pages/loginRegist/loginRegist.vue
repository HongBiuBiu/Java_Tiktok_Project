<style>
.page {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	background-color: #FFFFFF;
	padding: 20rpx;
	
	display: flex;
	flex-direction: column;
}
.login-box {
	display: flex;
	flex-direction: column;
	margin-top: 200rpx;
	padding: 20rpx;
}
.code-text {
	font-weight: 600;
	font-size: 60rpx;
}
.code-tips {
	font-weight: 300;
	font-size: 34rpx;
	color: #808080;
	margin-top: 20rpx;
}
.mobile-box {
	height: 100rpx;
	display: flex;
	flex-direction: row;
	padding: 0 20rpx;
	border-color: #808080;
	border-width: 1px;
}
.mobile-prefix {
	color: #808080;
	font-weight: 400;
	font-size: 36rpx;
}
.mobile {
	color: #0C0C0C;
	font-weight: 400;
	font-size: 38rpx;
}
.middle {
	width: 1rpx;
	background-color: #afafb3;
	height: 60rpx;
	margin: 0 40rpx;
}
.line {
	height: 1rpx;
	background-color: #afafb3;
}
.login-btn-text {
	font-size: 40rpx;
	color: #FFFFFF;
	font-weight: 500;
}
.code-btn-text {
	font-size: 30rpx;
	color: #FFFFFF;
	font-weight: 300;
}
.btn-login {
	background-color: #ef274d;
	/* border-color: #343438; */
}
.btn-login-touched {
	background-color: #e27c82;
	/* border-color: #000000; */
}
.btn-code {
	background-color: #0abb03;
}
.btn-code-touched {
	background-color: #6be866;
}
</style>

<template>
	<view class="page">
		<view :style="{height: statusBarHeight + 'px'}"></view>
		
		<image src="../../static/images/icon-close-black.png" style="width: 50rpx;height: 50rpx;" @click="close()"></image>
		
		<view class="login-box">
			<text class="code-text">Verification code login</text>
			<text class="code-tips">Note: After successful verification of an unregistered phone number, the system will automatically register it.~</text>
			
			<view class="mobile-box">
				<text class="mobile-prefix" style="align-self: center;">+86</text>
				
				<view class="middle" style="align-self: center;"></view>
				
				<input type="number" 
					class="mobile" 
					:value="mobile" 
					:model="mobile" 
					@input="typingMobile"
					placeholder="Please enter your phone number" 
					maxlength="11"
					style="align-self: center;"/>
			</view>
			<view class="line" style="height: 1rpx;background-color: #afafb3;"></view>
			
			<view class="mobile-box" style="margin-top: 20rpx;">
				<input type="number" 
					class="mobile" 
					:value="verifyCode"
					:model="verifyCode"
					@input="typingVerifyCode"
					placeholder="Please enter verification code" 
					maxlength="6" 
					style="width: 360rpx;align-self: center;"/>
				
				<view class="middle" style="align-self: center;"></view>
				
				<view
					:class="{'btn-code':!codeTouched, 'btn-code-touched': codeTouched}"
					@touchstart="touchstartCode"
					@touchend="touchendCode"
					@click="getCode()"
					style="border-width: 1px;border-color: #FFFFFF;width: 220rpx;height: 80rpx;border-radius: 60rpx;display: flex;flex-direction: row;justify-content: center;align-self: center;">
					<text class="code-btn-text" style="align-self: center;">{{codeBtnText}}</text>
				</view>
			</view>
			<view class="line"></view>
			
			<view 
				:class="{'btn-login':!loginTouched, 'btn-login-touched': loginTouched}"
				@touchstart="touchstartLogin"
				@touchend="touchendLogin"
				@click="loginOrRegist()"
				style="margin-top: 50rpx;border-width: 1px;border-color: #FFFFFF;width: 650rpx;height: 120rpx;opacity: 0.8;border-radius: 100rpx;display: flex;flex-direction: row;justify-content: center;align-self: center;">
					<text class="login-btn-text" style="align-self: center;">login / sign up</text>
			</view>
			
			<view style="display: flex;flex-direction: column;margin-top: 50rpx;">
				<text style="color: #808080;font-size: 30rpx;">*Attention：</text>
				<text style="color: #808080;font-size: 30rpx;">1. After launching the beta version, it will automatically log in. After logging out, you can log in or register using your phone number.</text>
				<text style="color: #808080;font-size: 30rpx;">2. The default test account for the beta version includes some built-in data that can be used for testing or viewing.</text>
				<text style="color: #808080;font-size: 30rpx;">3. The beta version cannot publish videos.</text>
				<text style="color: #808080;font-size: 30rpx;">4. In the beta version, messages and user information modifications will be automatically cleared periodically.</text>
			</view>
		</view>
	</view>
</template>

<script>
	var system = uni.getSystemInfoSync();
	var app = getApp();
	export default {
		data() {
			return {
				statusBarHeight: 0,
				
				mobile: "13812345678",
				verifyCode: "",
				codeBtnText: "Get verification code",
				codeTimes: 0,
				
				loginTouched: false,
				codeTouched: false,
			}
		},
		onLoad() {
			this.statusBarHeight = system.statusBarHeight;
		},
		methods: {
			touchstartLogin() {
				this.loginTouched = true;
			},
			touchendLogin() {
				this.loginTouched = false;
			},
			touchstartCode() {
				if(this.codeTimes > 0) {
					return;
				} else {
					this.codeTouched = true;
				}
			},
			touchendCode() {
				if(this.codeTimes > 0) {
					return;
				} else {
					this.codeTouched = false;
				}
			},
			close() {
				uni.navigateBack({
					delta: 1,
					animationType: "slide-out-bottom"
				})
			},
			typingMobile(e) {
				var event = e;
				this.mobile = e.detail.value;
			},
			typingVerifyCode(e) {
				var event = e;
				this.verifyCode = e.detail.value;
			},
			getCode() {
				var me = this;
				
				if(me.codeTimes > 0) {
					return;
				}
				
				me.codeTouched = true;
				
				var mobile = me.mobile;
				
				if (app.isStrEmpty(mobile) || mobile.length != 11) {
					uni.showToast({
						title: "Wrong phone number",
						icon: "none"
					});
					return;
				}
				
				var serverUrl = app.globalData.serverUrl;
				uni.request({
					method: "POST",
					url: serverUrl + "/passport/getSMSCode?mobile=" + mobile,
					success(result) {
						var status = result.data.status;
						if (status != 200) {
							uni.showToast({
								title: result.data.msg,
								icon: "none"
							});
						}
						
						if(me.codeTimes == 0) {
							me.doTimer(59);
						}
					}
				});
					
			},
			doTimer(times) {
				var me = this;
				var sendCodeBtnFunction = function(){
					var left = times--;

					if (left <= 0) {
						me.codeTouched = false;
						me.codeBtnText = "Send verification code";
						clearInterval(smsTimer);
					} else {
						me.codeBtnText = left + "s";
					}
					me.codeTimes = left;
				};
				var smsTimer = setInterval(sendCodeBtnFunction, 1000);
			},
			loginOrRegist() {
				var me = this;
				var verifyCode = me.verifyCode;
				var mobile = me.mobile;
				
				if (app.isStrEmpty(verifyCode)) {
					uni.showToast({
						title: "Please enter the verification code",
						icon: "none"
					});
					return;
				}
				
				if (app.isStrEmpty(mobile) || mobile.length != 11) {
					uni.showToast({
						title: "Wrong phone number",
						icon: "none"
					});
					return;
				}
				
				var serverUrl = app.globalData.serverUrl;
				uni.request({
					method: "POST",
					url: serverUrl + "/passport/login",
					data: {
						"mobile": mobile,
						"smsCode": verifyCode
					},
					success(result) {
						var status = result.data.status;
						if (status == 200) {
							var userInfo = result.data.data;
							app.setUserInfoSession(userInfo);
							app.setUserSessionToken(userInfo.userToken);
							// me.close();
							uni.reLaunch({
								url: "../me/me"
							})
						} else if (status != 200) {
							uni.showToast({
								title: result.data.msg,
								icon: "none",
								duration: 3000
							});
						}
					}
				});
			}
		}
	}
</script>


