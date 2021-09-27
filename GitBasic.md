# Git 기초

- Git은 분산버전관리시스템으로 코드의 버전을 관리하는 도구로, 컴퓨터 파일의 변경사항을 추적하고 여러 명의 사용자들 간 해당 파일의 작업을 조율한다.



## Git의 기본 흐름

1. 파일을 작업(수정)
2. Staging area에 Add
3. Commit하여 버전 기록

Git은 파일을 modified, staged, committed하여 관리한다.

- modified : 파일이 수정된 상태로 add 명령어를 통하여 Staging area로 변경한다
- Staged : 수정한 파일을 곧 커밋할 것이라고 표시한 상태로 commit 명령어를 통하여 저장소로 
- committed : 커밋 된 상태



## Git의 기본 명령어



### init

- $git init : 특정 폴더를 git 저장소(repository)를 만들어 git으로 관리한다.
- git bash 에서는 (master)라는 표기를 확인할 수 있다.

```bash
$git init
```



### add

- $gid add <file> : directory 상의 변경 내용을 Staging area에 추가하기 위해 사용한다 
- 모든 파일을 add 하려면 . 특정 파일을 add 하려면 $git add 파일명

```bash
$git add . 
$git add Test.txt
```



### commit

- $git commit : Staged 상태의 파일들을 커밋을 통해 버전으로 기록한다.
- 커밋 메시지는 변경 사항을 나타낼 수 있도록 명확하게 작성해야 한다.

```bash
$git commit -m Add Test.txt
```

[커밋 메시지 영어사전]([좋은 git commit 메시지를 위한 영어 사전 (ull.im)](https://blog.ull.im/engineering/2019/03/10/logs-on-git.html))



### status

- $git status : Git 저장소에 있는 파일의 상태를 확인하기 위해 활용한다 
- Untracked files / Changes not staged for commit / Changes to be committed 등으로 파일의 상태를 알 수 있다.

```bash
$git status
```



### log

- $git log : 현재 저장소에 기록된 커밋을 조회하기 위하여 활용한다.



$git log -1 : 최근 한 개의 결과를 출력한다

```bash
$git log -1
```

$git log --oneline : 한줄 씩 간단하게 출력한다

```bash
$git log --oneline
```

다양하게 조합하여 활용할 수 있다.

```bash
$git log -2 --oneline
```



## 원격저장소 활용 명령어

### push

```bash
$git push origin master
```

