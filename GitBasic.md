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



# push error

## 에러 상황

```bash
$ git push origin master
To https://github.com/edutak/0928.git
 ! [rejected]        master -> master (fetch first)
error: failed to push some refs to 'https://github.com/edutak/0928.git'
# Updates 거절.
# 원격저장소 작업이 로컬에 없어서 
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
# 다시 push하기 전에, 원격저장소 변경사항들을 먼저 통합하는 것을 원할 것..
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.

```

## 해결

### 1. pull

```bash
$ git pull origin master
remote: Enumerating objects: 4, done.
remote: Counting objects: 100% (4/4), done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 3 (delta 1), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (3/3), 2.04 KiB | 208.00 KiB/s, done.
From https://github.com/edutak/0928
 * branch            master     -> FETCH_HEAD
   2a71213..c143740  master     -> origin/master
hint: Waiting for your editor to close the fiMerge made by the 'recursive' strategy.
 22.md | 159 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 1 file changed, 159 insertions(+)
 create mode 100644 22.md

```

* 커밋 메시지(merge)가 발생

  ```bash
  $ git log --oneline
  9f0d621 (HEAD -> master) Merge branch 'master' of https://github.com/edutak/0928
  772310c Add d.txt
  c143740 (origin/master) Add files via upload
  2a71213 Update
  
  ```

  

* 충돌 발생시 해결이 필요

### 2. push

```bash
$ git push origin master
Enumerating objects: 6, done.
Counting objects: 100% (6/6), done.
Delta compression using up to 12 threads
Compressing objects: 100% (4/4), done.
Writing objects: 100% (4/4), 482 bytes | 482.00 KiB/s, done.
Total 4 (delta 2), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (2/2), completed with 1 local object.
To https://github.com/edutak/0928.git
   c143740..9f0d621  master -> master
```









