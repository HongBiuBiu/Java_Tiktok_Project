<script>
	import provinceList from './json/area_province.js';
	import cityList from './json/area_city.js';
	import districtList from './json/area_district.js';
	export default {
		globalData: {  
			serverUrl: "http://192.168.1.154:9999",		
			
			provinceList: provinceList,
			cityList: cityList,
			districtList: districtList,
		},
		
		onLaunch: function() {
			
			console.log("onLaunch...Testing the printing of debugging information");
			
			
			var me = this;
			uni.onTabBarMidButtonTap(()=>{
				
				var myUserInfo = getApp().getUserInfoSession();
				if (myUserInfo == null) {
					uni.showToast({
						duration: 3000,
						title: "Please login~",
						icon: "none"
					});
					uni.navigateTo({
						url: "../loginRegist/loginRegist",
						animationType: "slide-in-bottom",
						success() {
							me.loginWords = "Please login"
						}
					});
					return;
				}
				
				// console.log('onTabBarMidButtonTap');
				uni.switchTab({
					url: "../me/me"
				});
				
				uni.chooseVideo({
					sourceType: ['album'],
					success(e) {
						
						uni.navigateTo({
							url: "/pages/publish/publish?fileObjectEvent=" + JSON.stringify(e)
						})
					
						var times = new Date().getTime();
					}
				})
				
			});

		},
		onShow: function() {
		},
		onHide: function() {
		},
		methods: {
			getDefaultVlogList() {
				var list = [
				];
				return list;
			},
			graceNumber(number) {
				if (number == 0) {
					return "0";
				} else if (number > 999 && number <= 9999) {
					return (number/1000).toFixed(1) + 'k';
				} else if (number > 9999 && number <= 99999) {
					return (number/10000).toFixed(1) + 'w';
				} else if (number > 99999) {
					return "10w+";
				} 
				return number;
			},
			getDateBeforeNow(stringTime) {
				// console.log(stringTime);
				stringTime = new Date(stringTime.replace(/-/g,'/'))
				
				var minute = 1000 * 60;
				var hour = minute * 60;
				var day = hour * 24;
				var week = day * 7;
				var month = day * 30;
		
				var time1 = new Date().getTime(); 
				// console.log(time1);
				// console.log(new Date(stringTime));
				var time2 = Date.parse(new Date(stringTime)); 
				// console.log(time2);
				var time = time1 - time2;
		
				var result = null;
				if(time < 0) {
					// alert("The time set cannot be earlier than the current time!");
					result = stringTime;
				}else if(time/month >= 1){
					result = parseInt(time/month) + "months ago";
				}else if(time/week >= 1){
					result = parseInt(time/week) + "weeks ago";
				}else if(time/day >= 1){
					result = parseInt(time/day) + "days ago";
				}else if(time/hour >= 1){
					result = parseInt(time/hour) + "ago";
				}else if(time/minute >= 1){
					result = parseInt(time/minute) + "ago";
				}else {
					result = "just now";
				}
				// console.log(result);
				return result;
			},
			
			isStrEmpty (str) {
				if (str == null || str == undefined || str == "") {
					return true;
				} else {
					return false;
				}
				
			},
			userIsLogin() {
				var userToken = this.getUserSessionToken();
				var userInfo = this.getUserInfoSession();
				// console.log("userInfo=" + userInfo);
				// console.log("userToken=" + userToken);
				if (!this.isStrEmpty(userToken) && !this.isStrEmpty(userInfo)) {
					return true;
				} else {
					return false;
				}
			},
			setUserSessionToken(token){
				uni.setStorageSync("userToken", token);
			},
			getUserSessionToken() {
				var token = uni.getStorageSync("userToken");
				if (this.isStrEmpty(token)) {
					return "";
				}
				return token;
			},
			setUserInfoSession(user){
				uni.setStorageSync("userInfo", JSON.stringify(user));
			},
			getUserInfoSession() {
				var user = uni.getStorageSync("userInfo");
				if (this.isStrEmpty(user)) {
					return null;
				}
				return JSON.parse(user);
			},
			clearUserInfo () {
				uni.removeStorageSync("userInfo");
				uni.removeStorageSync("userToken");
			},
			
			// let date = new Date()
			// dateFormat("YYYY-mm-dd HH:MM", date)
			dateFormat(fmt, date) {
			    let ret;
			    const opt = {
			        "Y+": date.getFullYear().toString(),        
			        "m+": (date.getMonth() + 1).toString(),     
			        "d+": date.getDate().toString(),          
			        "H+": date.getHours().toString(),       
			        "M+": date.getMinutes().toString(), 
			        "S+": date.getSeconds().toString()    
			    };
			    for (let k in opt) {
			        ret = new RegExp("(" + k + ")").exec(fmt);
			        if (ret) {
			            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
			        };
			    };
			    return fmt;
			},
			
			testAstro(month, day){
				console.log(month + "month" + day + "day: " + this.getAstro(month, day));
			},
		}
		
	}
</script>

<style>
	.activate-line {
		background-color: #FFFFFF;
	}
</style>
