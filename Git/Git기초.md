# Git

## 저장소 생성 (`init`)

```bash
$ git init
Initialized empty Git repository in C:/Users/student/Desktop/test/.git/
```

* `.git` 저장소가 생성되고, CLI 환경에서는 `(master)` 브랜치가 나타난다.

## 버전 관리

> 프로젝트 폴더 내에서 파일/폴더의 변경사항을 버전으로 기록

### `add`

```bash
$ git add <file> 
$ git add .             # 현재 디렉토리
$ git add a.txt b.txt   # 특정 파일
$ git add my_folder/    # 특정 폴더
$ git add *.md          # 특정 확장자
```

#### status 살펴보기

```bash
$ touch README.md
$ git status
On branch master

No commits yet
# 트래킹 X 파일들(한번도 버전관리에 되지 않은 파일) ex) 파일 생성..
# Working Directory(첫번째통!)
Untracked files:
  (use "git add <file>..." to include in what will be committed)
        README.md

nothing added to commit but untracked files present (use "git add" to track)

```

```bash
$ git add .
$ git status
On branch master

No commits yet
# 커밋될 변경사항들
# Staging Area (staged 상태인 파일들)
Changes to be committed:
  (use "git rm --cached <file>..." to unstage)
        new file:   README.md

```

### `commit`

```bash
$ git commit # 메시지 편집 창(기본 Vim)
$ git commit -m '메시지'
[master (root-commit) 53dc80f] Add README.md
 1 file changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 README.md
 
$ git log
commit 53dc80f4be3fa19e749ab6a8558e4b98c8f12717 (HEAD -> master)
Author: edutak <edutak.ssafy@gmail.com>
Date:   Mon Sep 27 15:12:11 2021 +0900

    Add README.md
```

## 추가 명령어 (상태)

### `status`

> Working directory(untracked, Changes not staged for commit), Staging area(Changes to be committed)의 상태를 보여줌. 

```bash
$ git status
On branch master
nothing to commit, working tree clean
```

### `log`

> 커밋을 조회

```bash
$ git log # 전체 
commit 53dc80f4be3fa19e749ab6a8558e4b98c8f12717 (HEAD -> master)
Author: edutak <edutak.ssafy@gmail.com>
Date:   Mon Sep 27 15:12:11 2021 +0900

    Add README.md

$ git log -2 # 최근 2개
$ git log --oneline # 간략하게
53dc80f (HEAD -> master) Add README.md

$ git log --oneline -2 # 최근 2개를 간략하게
```

## 원격저장소 관리

> 원격저장소를 제공하는 서비스는 다양하지만, GitHub을 사용

### 원격저장소 등록

```bash
$ git remote add origin https://github.com/username/repository_name.git
```

* `origin` 이름으로 특정 URL을 등록

  * 깃아, 원격저장소(`remote`) `추가` 해줘. `origin` 이름으로 `URL` 을

* 오류 메시지 : 특정 이름으로 이미 등록된 경우. 에러가 발생

  * url을 변경(`set-url`)하거나 해당 원격저장소를 삭제하고 다시 등록하는 명령어를 사용해서 변경

  ```bash
  # 기존 URL을 잘못 지정
  $ git remote add origin https://github.com/username/test.git
  error: remote origin already exists. # 덮어쓰기 X
  $ git remote rm origin # 삭제
  $ git remote add origin https://github.com/username/test.git # 다시
  
  ```

### 원격저장소 `push`

> 파일/폴더가 업로드 되는 것이 아니라 버전(커밋들)을 push하는 것!

```bash
$ git push origin master
```

* `origin` 으로 지정된 원격저장소의 `master`로 `push`

* 오류메시지 : 커밋 혹은 브랜치 없는 경우 

   ```bash
   $ git push origin master
   error: src refspec master does not match any
   error: failed to push some refs to 'https://github.com/username/test.git'
   ```

### 원격저장소 목록

```bash
$ git remote -v
```

 



















