TARGET=../match-deploy
mkdir $TARGET
cp -rf match-api/ $TARGET/
cp -rf match-web/app/ $TARGET/src/main/resources/static/
mv -f $TARGET/src/main/resources/application-heroku.properties $TARGET/src/main/resources/application.properties
cat >$TARGET/Procfile <<EOF
web: java -Dserver.port=\$PORT -jar target/*.jar
EOF
chmod +x $TARGET/Procfile
