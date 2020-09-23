image_name = walmart-back

gradle_cmd += ./gradlew
gradle_cmd += --no-daemon
gradle_cmd += --parallel

assemble:
	$(gradle_cmd) clean assemble

clean:
	$(gradle_cmd) clean

run: assemble
	$(gradle_cmd) bootRun

unit-test:
	$(gradle_cmd) test

integration-test:
	$(gradle_cmd) cleanIntegrationTest integrationTest

test: unit-test integration-test

docker:
	docker build -t wallmart-test-back .

docker-build-tasker:
	docker build \
		-t farcis/${image_name} \
		-f docker/Dockerfile.runner \
		.

docker-run: docker-build-tasker
	docker run \
		--interactive \
		--name promo-code-api \
		--publish 8080:8080 \
		--rm \
		farcis/${image_name}
		$(gradle_cmd)

docker-unit-test: docker-run
	$(gradle_cmd) test

docker-integration-test: docker-run
	$(gradle_cmd) cleanIntegrationTest integrationTest






