# JASON 데이터 가공 예제 in JS



## JSON DATA

```json
{    "predictions": [        {            "0": {                "score": 0.8439271450042725,                "x": 0.594059405940594,                "y": 0.33064516129032256            },            "1": {                "score": 0.8092899918556213,                "x": 0.48514851485148514,                "y": 0.4112903225806452            },            "2": {                "score": 0.8194109797477722,                "x": 0.42574257425742573,                "y": 0.4032258064516129            },            "3": {                "score": 0.962486982345581,                "x": 0.5346534653465347,                "y": 0.49193548387096775            },            "4": {                "score": 0.7953050136566162,                "x": 0.6534653465346535,                "y": 0.3951612903225806            },            "5": {                "score": 0.6566188335418701,                "x": 0.5544554455445545,                "y": 0.41935483870967744            },            "6": {                "score": 0.8413869738578796,                "x": 0.7029702970297029,                "y": 0.5            },            "7": {                "score": 0.7202619910240173,                "x": 0.7029702970297029,                "y": 0.4032258064516129            },            "8": {                "score": 0.6882699728012085,                "x": 0.2871287128712871,                "y": 0.6129032258064516            },            "9": {                "score": 0.7755789756774902,                "x": 0.4158415841584158,                "y": 0.6774193548387096            },            "10": {                "score": 0.8374090194702148,                "x": 0.32673267326732675,                "y": 0.8951612903225806            },            "11": {                "score": 0.6217861771583557,                "x": 0.39603960396039606,                "y": 0.6129032258064516            },            "12": {                "score": 0.8287659883499146,                "x": 0.5841584158415841,                "y": 0.6451612903225806            },            "13": {                "score": 0.8679889440536499,                "x": 0.5247524752475248,                "y": 0.8225806451612904            },            "14": {                "score": 0.8959919810295105,                "x": 0.5643564356435643,                "y": 0.31451612903225806            },            "15": {                "score": 0.8276309967041016,                "x": 0.6039603960396039,                "y": 0.31451612903225806            },            "16": {                "score": 0.8946890234947205,                "x": 0.49504950495049505,                "y": 0.31451612903225806            }        }    ]}
```









## JS

```javascript
  $("#poseBtn").click(function (){
        const data = new FormData();
        data.append("image", $("#poseImg").prop('files')[0]);


        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "../pose",
            data: data,
            processData: false,
            contentType: false,

            success: function (result) {
                const data = JSON.parse(result).predictions[0];

                // for(let k in data) {
                //     console.log(data[k].score)
                // }
                console.log(data[0])

                $("#poseDiv").append("nose = "+JSON.stringify(data[0]))
                $("#poseDiv").append("neck = "+JSON.stringify(data[1]))
            },

            error: function (e) {
                console.log("ERROR : ", e)
                console.log("Fail")
            }
        })
    })

```





## JAVA Controller

```java
@PostMapping("pose")
    @ResponseBody
    public String pose(MultipartFile image) {
        if (image != null) {
            String imgName = image.getOriginalFilename();
            if (imgName != "") {
                imgName = "C:/temp3/" + imgName;
                File dest = new File(imgName);
                try {
                    image.transferTo(dest);


                    return poseService.pose(imgName);
                } catch (IllegalStateException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return "fail";
    }
```

