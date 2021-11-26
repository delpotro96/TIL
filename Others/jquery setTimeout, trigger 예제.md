# jquery setTimeout, trigger 예제



```javascript
$("#mic").click(function(){
			$("#mic").attr("src","../img/mic_on_small.png");
			recognition.start();

			setTimeout(function(){
				$("#mic").attr("src","../img/mic_off_small.png");
				recognition.stop();

				$("#tempBtn").trigger("click");

				const text=$("#inputField").val();


				$.post("../stt",{text},function(data){
					const audio=new Audio("../media/"+data+".mp3");
					audio.play();
				});
			}, 3000);
		});
```

