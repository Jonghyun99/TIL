# [Git] Commit History 정리하기 (Rebase, Sublime Merge)

프로젝트를 끝마친 뒤 반성점 중 하나가 Commit History 관리였다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled.png)

실제로 작업한 프로젝트의 커밋로그인데 341개의 커밋로그 중 로그로서 온전한 기능을 하는 커밋은 절반도 안될 것이다. 프로젝트의 규모가 커지고 협업하는 사람이 많아질 수록 로그를 잘 관리해야 할 필요성이 커질 것이다. 이번 기회에 그 방법에 대해 공부하자.

## Rebase에 대해

커밋히스토리 관리는 rebase 명령어 하나로 해결된다.

rebase의 기능은 특정 브랜치에서 분기되는 커밋로그를 정렬 하는 것이다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%201.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%201.png)

만약 위와같은 b에서 분기된 커밋로그에서 f2와 m2를 병합하고자 한다.

이 때 rebase를 사용하면 base를 b가 아닌 m2로 재설정 할 수 있다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%202.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%202.png)

rebase과정에서 위와 같은 결과를 만들고 싶으면 commit HEAD가 feature를 보고 있어야 한다.

(애초 rebase는 커밋로그를 덮어쓰는 명령이기 때문에 master에서 rebase하는 것을 지양하며 항상 서브 브런치에서 작업하여야 한다는 사실을 이해하고 있으면 금방 외울 수 있다.)

## 연습해보기

원래 Eclipse에서 지원하는 git 툴을 사용했는데 이번 기회에 Sublime Merge라는 관리 툴로 연습해보며 함께 사용해보기로 했다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%203.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%203.png)

우선 연습용 저장소를 새로 파서 master 브랜치에 커밋로그 2개를 생성했다.

GUI 툴과 연동해보자.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%204.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%204.png)

로컬에 미리 연결해둔 저장소와 연동된 모습이다. 내가 커밋한 로그와 미리 만들어둔 브랜치도 함께 보인다.

(캡처를 못했는데 처음 화면에서 Open Repository를 눌러서 연결했다.)

일단 기존 사용하던 단순 merge를 사용하여 실습해보기 위해 master브랜치에 C, D파일을 커밋하고 

merge_branch에 E, F파일을 커밋한 뒤 merge한 모습을 살펴보겠다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%205.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%205.png)

로컬 저장소에 파일을 작성하니 별도의 fetch없이도 실시간으로 자료를 연동해온다.

 Stage All를 눌러 두 파일을 Commit해보자.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%206.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%206.png)

커밋이 잘 올라갔다. 사진에서 보이다시피 두번째커밋에서 merge_branch가 생성된 것을 볼 수 있다.

이제 merge_branch에서 네번째 커밋을 하고 master와 merge로 병합하면 아마 흔히 볼 수 있는 지하철 노선도 같은 커밋로그가 생성될 것이다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%207.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%207.png)

예상대로 두 번째커밋을 베이스로 분기된 커밋로그가 생성됐다.

### Rebase

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%208.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%208.png)

이번엔 아까와 같은 상황에서 rebase_branch로 두 번째 커밋에서 분기시켰다.

rebase_branch에서 5, 6번째의 커밋을 한 뒤 아까와는 다르게 merge가 아니라 rebase 명령어로 병합해보겠다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%209.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%209.png)

rebase_branch에서도 커밋을 했다. 내 목적은 HEAD가 rebase_branch를 보고있는 상태에서 master와 rebase 를 통해 커밋 내역을 정렬한 뒤에 다시 master로 merge하여 한줄짜리 커밋 히스토리를 만드는 것이다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2010.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2010.png)

원하는 대로 만들어졌다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2011.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2011.png)

커밋이 정렬되는 순서에 대해 잘 이해가 안가서 master에서 먼저 커밋로그를 남기고 rebase했던 아까와는 반대로 rebase_branch에서 먼저 커밋을 남기고 rebase를 진행해 보았다.

예상대로 rebase의 대상인 master브랜치의 마지막 커밋을 기준으로 뒤에 덧붙여졌다.

이로써 rebase 명령어는 대상 브랜치의 마지막 커밋을 base로 정렬한다는 사실을 알 수 있게 됐다.

그렇다면 우리가 실제로 작업할 때에는 가장 마지막으로 작업한 브랜치에 checkout을 한 뒤 먼저 작업한 브랜치를 대상으로 rebase 명령어를 날려주면 작업한 시간 순서대로 깔끔하게 정렬할 수 있을 것이다.

### Squash

하나의 작업을 3~4번 나누어서 커밋을 하게된 경우에 Squash 옵션을 사용한다.

Squash는 쪼개어진 커밋을 하나로 합칠 수 있는 rebase의 옵션이다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2012.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2012.png)

위와 같이 master 브랜치에 squash_branch 브랜치를 병합하려는데 커밋이 2번 나뉘어서 올라왔다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2013.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2013.png)

gui tool에서는 옵션을 체크하여 squash를 진행할 수 있다.

체크하고 Merge를 누르면

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2014.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2014.png)

이렇게 2번 나뉘어 커밋했던 파일을 한 커밋에 담을  수 있는걸 확인할 수 있다.

이대로 병합을 해보자.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2015.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2015.png)

master 브랜치에는 두 커밋이 하나의 커밋으로 업로드 되었다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2016.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2016.png)

git hub에서 확인한 모습이다.

병합할 때 squash를 사용하면 좋은점이 쓸모없는 로그를 없앨 수 있다는 점이다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2017.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2017.png)

병합하거나 PULL 할 때에도 위와 같이 원하지 않는 로그가 생길 수 있다.

![%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2018.png](%5BGit%5D%20Commit%20History%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20(Rebase,%20Sublime%20Me%20f3a01d5cea1744fc8b8167ee7cb127c1/Untitled%2018.png)

이 때 rebase를 이용해 필요없는 로그를 지울 수 있다.

다만 한 브랜치에서 로그를 없애고 싶을 경우에는 git bash를 통한 명령어로 해결할 수 있다.

(sublime merge에는 아무리 찾아봐도 안보임)

rebase에는 커밋 메시지 수정이나 내용 수정 등 더 다양한 기능이 있지만 여기까지만 익혀둬도 고급스럽게 git을 사용할 수 있을거 같다.

이번에 GUI TOOL을 처음 다뤄봤는데 디테일한 기능들은 BASH를 통해 사용해야겠지만 하지만 웬만해서는 GUI로도 커버가 될거 같다.

출처 : [https://velog.io/@godori/Git-Rebase](https://velog.io/@godori/Git-Rebase) (rebase에 대해, rebase 자료 사진)