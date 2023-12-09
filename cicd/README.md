# CICD
GitlabとTektonを利用したCICDを構築します。

## 環境セットアップ
### GitLab
- 環境スタートアップ
  '''
  SET GITLAB_HOME=C:\Users\akito\gitlab
  docker run --detach ^
  --hostname gitlab.example.com ^
  --publish 443:443 --publish 8000:80 --publish 22:22 ^
  --name gitlab ^
  --restart always ^
  --volume %GITLAB_HOME%/config:/etc/gitlab ^
  --volume %GITLAB_HOME%/logs:/var/log/gitlab ^
  --volume %GITLAB_HOME%/data:/var/opt/gitlab ^
  --shm-size 256m ^
  gitlab/gitlab-ce:16.4.1-ce.0
  '''
- ログイン
  kumaki/Donchan0225
  http://127.0.0.1:8000/

