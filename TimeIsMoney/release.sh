#!/bin/bash
ant release
pushd bin
jarsigner -verbose -sigalg MD5withRSA -digestalg SHA1 -keystore ../my-release-key.keystore  -sigalg MD5withRSA  TimeIsMoney-release-unsigned.apk TimeIsMoney
zipalign -f -v 4 TimeIsMoney-release-unsigned.apk TimeIsMoney-release.apk
popd
echo `pwd`/bin/TimeIsMoney-release.apk | xclip -selection clipboard
