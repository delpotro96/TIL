# jquery 동일 클래스 다른 밸류일 때



- 장바구니 담기 상황에서 담기 버튼의 class에 basketBtn 부여 
- 장바구니 담기를 누르면 공백 2칸 후 이어져서 나오도록

```javascript
$(document).ready(function () {
            let id = "";
            $(".basketBtn").click(function () {
                id += $(this).val() + "  ";
                $("#text").val(id)
                tts($(this).val());
            });
        });
```

