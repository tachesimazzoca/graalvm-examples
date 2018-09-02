# graalvm-examples-polyglot

This example requires [GraalVM](https://www.graalvm.org/). You might specify the project or module SDK on your IDE to use it.

```
$ ${GRAAL_VM_DIR}/jre/bin/java -version
openjdk version "1.8.0_172"
OpenJDK Runtime Environment (build 1.8.0_172-20180626105433.graaluser.jdk8u-src-tar-g-b11)
GraalVM 1.0.0-rc5 (build 25.71-b01-internal-jvmci-0.46, mixed mode)
```

Some examples depends on [TruffleRuby](https://www.graalvm.org/docs/reference-manual/languages/ruby/). Use the `gu` command to add ruby.

```
$ gu install ruby
...
# will be installed at:
$ ls -la ${GRAAL_VM_DIR}/jre/languages/ruby
```

