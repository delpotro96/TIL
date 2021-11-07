# GIt Config

## Config

- 기본 에디터 : vim > visual studio code로 변경

```bash
$git config --global core.editor "code --wait"
```

## .gitignore

- .gitignore 를 이용하여 git으로 관리하지 않을 파일 목록을 관리

```bash
# 특정 파일
data.csv
a.txt
# 특정 폴더
secret/
address/
# 특정 확장자 (*)
*.csv
*.pptx
```

- 일반적인 개발 환경에서 필수적으로 등록하는 예시
  - OS, IDE(eclipse), Text editor, 특정 프레임워크에서 생성되는 파일
    - https://github.com/github/gitignore/blob/master/Java.gitignore
    - https://gitignore.io/ 

      * 예시) `windows`, `java`, `eclipese`