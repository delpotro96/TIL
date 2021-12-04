# React & Spring



1. 요청 -> VIEW(html) -> 화면 통째로
2. VIEW -> 요청(js로) -> ajax로 요청 -> json으로 응답 -> 그림 그리기 javascript
3. 리액트 -> ajax -> 데이터 변경 감지 -> ui가 자동 업데이트

리액트는 쉽게 말해 변경을 감지하는 엔진(Daemon)/ 서버처럼 계속 돌아야 됨

node.js 위에서 돔







## NPM, NPX

- 둘다 라이브러리를 다운 받은 후 빌드한다는 공톰점이 있음



### NPM

- 라이브러리 다운 > 빌드 > 로컬에 모두 다운



### NPX

- 라이브러리 다운 > 빌드 > 다운 받은 게 있으면 재사용 없으면 다운
- 실행 받은 후 삭제 



```react
npx create-react-app my-app
cd my-app
npm start
```



### package.json

- package.json은 패키지 실행을 관리함





### SPA(Single Page Application)

- index.html만 열림
- a 태그 안먹힘



### JSX 문법

- JSX 문법은 javascript에 html을 넣기 위해 만들어짐 jsp와 비슷
- 자바스크립트에 html에 넣는 문법



- 리액트는 무조건 하나의 태그만 리턴할 수 있음 <div></div><h1></h1> 두개 쓰기 안됨 

  대신 하나의 태그 안에 넣기는 씹가능 <div><h1></h1></div>

- 변수 선언은 let, const로만 해야됨 var 쓰지마삼 (스코프가 꼬임)

- const는 상수 let은 변수 선언 후에 사용할 때는 

- ```react
  let a = 10;
  const b = 20;
  function App() {
   return<div>
     안녕 {a}
     <h1>헤딩태그 {b} </h1>
   </div>;
  }
  ```

- #### 삼항연산자 > {조건? 값(true): 값(false)}

  ```react
  const a = 10
  {a===10?'10입니다':'10이 아닙니다'}
  ```

  

- #### 조건부 렌더링 

- ```react
   {b===20&&'20입니다'}
  ```



### CSS 적용 방법

- #### 내부에 적는 방법

- ```react
  const mystyle = {
    color: "red",
  }
  
  function App() {
   return(
   <div>
     <div style={mystyle}>안녕 {a===10?'10입니다':'10이 아닙니다'}</div>
  
     <h1>헤딩태그 {b==20&&'20입니다'} </h1>
   </div>
   )
  }
  ```

- #### 외부 파일에 적는 방법(추천)

- App.css

- ```react
  .box-style {
    color: blue;
  }
  
  ```

- App.js

- ```react
  <h1 className="box-style">헤딩태그 {b===20&&'20입니다'} </h1>
  ```



### 불변함수 (깊은 복사 함수)

- 깊은 복사하면 데이터와 레퍼런스가 같아도 리빌드함



#### concat (Add 할 때)

```react
const a2 = [1, 2, 3];
const b2 = a2.concat(4);
const c2 = [0, ...a, 4];
console.log('a2의 값은: ${a2}'); // 1, 2, 3
console.log('b2의 값은: ${b2}'); // 1, 2, 3, 4
console.log('c2의 값은: ${c2}'); // 0, 1, 2, 3, 4
```



#### filter (Remove 할 때)

```react
const a3 = [1, 2, 3];
const b3 = a3.filter((n)=>{return n!=1;}); //boll을 return ->true만 걸러낸다
console.log(b3) // 2, 3
```



#### map(반복하기)

```react
const a5 = [1, 2, 3];
a5.forEact( ( n )=>{ console.log(n); }; ) // forEach 문은 리턴 못함


const b5 = a5.map( ( n ) => n); //중괄호 안쓰면 return 생략 가능 const b5 =[...a5]
console.log(b5)/// 1, 2, 3   
```

- 그냥 값을 복사할 거면 스프레드 연산자가 좋지만 map은 데이터 가공이 가능함



```react
function App() {
  
  let list = [1, 2, 3];
  
  return(
    <div>
      <div> {list.map((n)=> <h1>{n}</h1>)} </div>
    </div>
 )
}
```

result =![image-20211203211812190](C:\Users\Kyunghun Lee\AppData\Roaming\Typora\typora-user-images\image-20211203211812190.png)





#### slice (데이터를 중간에 삽입할 때)

```react
const a4 = [1, 2, 3];
const b4 = a4.slice(0, 2)
console.log('b4의 값은 ${b4}') //1, 2  [1, 2]
const c4 = [...a4.slice(0,2), 4, ...a4.slice(2,3)];
console.log(c4) /// [1, 2, 4, 3]
```





#### 스프레드 연산자 (복사할 때)

```react
const a = [1,2,3];
const b = [...a];
b.push(4); //b의 데이터 변경
const c = [...a, 4]
console.log('a의 값은 ${a}') // 1, 2, 3
console.log('b의 값은 ${b}') // 1, 2, 3, 4
console.log('c의 값은 ${c}') // 1, 2, 3, 4


const a6 = {id:1, name:"홍길동"}
const b6 = {...a, name:"임꺽정"}
console.log(b6) // id:1, name:임꺽정  // 홍길동을 임꺽정으로 덮어씌움

//즉 사용자 정보 수정 요청이 들어왔을 경우
const userInfo = {id:"asdf", name:"park", phone:"1111"} 
// 번호만 phone만 수정
const newUserInfo = {...userInfo, phone:"2222"}
console.log(newUserInfo) // id:"asdf", name:"park", phone:"2222"
```



#### Use State

- 버튼을 클릭할 때 마다  number는 ++ but 랜더링 안됨 

```react
function App() {
  

  let number = 1;
  const add = () => {
    number ++;
    console.log("Add", number)
  }

  //랜더링 시점 = 상태값 변경
  return(
    <div>
      <h1>숫자 : {number}</h1>  
      <button onClick={add}>더하기</button>
    </div>
 )
}
```



- use state

```react
function App() {  

  const [number, setNumber] = useState(2); //React 안에 hooks 라이브러리
  
  const add = () => {
    setNumber(number+1); // 리액트한테 number 값 변경할게 라고 요청
    console.log("Add", number)
  }

  //랜더링 시점 = 상태값 변경
  return(
    <div>
      <h1>숫자 : {number}</h1>  
      <button onClick={add}>더하기</button>
    </div>
 )
}
```



#### useEffect

```react
function App() {
  
  const [data, setData] = useState(0);

  // 실행시점 : (1) App() 함수가 최초 실행될 때(그림이 그려질 때)
  //           (2) 상태변수가 변경될 때
    		  
  useEffect(()=>{
    console.log("useEffect 실행됨")
  });

  return (
    <div>
      <h1> 데이터 :{data}</h1>
      <button onClick={()=>{setData(data+1)}}>더하기</button>  
    </div>
  )
  
}

export default App;

```



- 의존성에 빈 값을 넣어주면 상태변수의 변경에 의존하지 않음, 의존성에 변경해야 하는 값을 넣어주면 유즈이펙트를 실행함

```react
useEffect(()=>{
    console.log("useEffect 실행됨")
    download();    

  }, []);
```







