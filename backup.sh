


mkdocs build --clean
rm -f doc/index.html
cp -f index.html doc/
rm -rf doc/test
cp -a test_v2 doc/test
cp -a doc/email/*  doc/email_temp

#cp -a doc/email_temp/*  doc/email

