.PHONY: build
build:
	./gradlew clean build

.PHONY: run
run:
	./build/install/app/bin/app

.PHONY: run-dist
run-dist:
	./gradlew clean
	./gradlew installDist
	./build/install/app/bin/app -h

.PHONY: test
test:
	./gradlew test
