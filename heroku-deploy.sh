TARGET=../match-deploy
mkdir $TARGET
cp -rf match-api/ $TARGET/
cp -rf match-web/app/ $TARGET/src/main/resources/static/
cat >$TARGET/Procfile <<EOF
web: java -Dspring.profiles.active=heroku -Dserver.port=\$PORT -jar target/*.jar
EOF
chmod +x $TARGET/Procfile
