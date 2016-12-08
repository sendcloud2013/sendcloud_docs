


mkdocs build --clean
rm -f doc/index.html
cp -f index.html doc/
rm -rf doc/test
cp -a test_v2 doc/test
#cp -a doc/email/*  email_temp/


cp -a email_temp/*  doc/email/
rm -rf email_temp/*

