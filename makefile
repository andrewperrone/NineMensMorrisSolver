default:
	java driver.java $(ARGS)
open:
	code -r *.java
	cd Report;\
	code -r *.md
commit:
	git add -A
	git commit -am "${ARGS}"
	git push