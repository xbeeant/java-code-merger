### java 代码合并工具
使用 javaparser 对java代码进行合并

参考 [https://github.com/beihaifeiwu/dolphin](https://github.com/beihaifeiwu/dolphin) 项目，在原有项目的基础上进行代码梳理（按自己的理解），并且对javaparser进行升级

### 使用

```java
JavaParser javaParser = new JavaParser();
ParseResult<CompilationUnit> parse1 = javaParser.parse(file1);
ParseResult<CompilationUnit> parse2 = javaParser.parse(file2);
CompilationUnit merge = JavaMerger.merge(parse1.getResult().get(), parse2.getResult().get(), true);
String result = merge.toString();
```