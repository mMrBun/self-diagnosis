#快速开始
拉取项目到本地
~~~bash
git clone https://github.com/mMrBun/self-diagnosis.git
~~~
配置maven等待依赖加载完毕
Neo4j数据在项目src/main/resource/database文件夹下，请将压缩包上传至Neo4j的import文件夹下并解压，然后输入命令
~~~bash
# 确保所有的数据文件都已经在import目录下之后运行下面的命令，如果是在Windows中运行的docker请使用下面的单行模式
docker exec -it neo4j bin/neo4j-admin import \
 --multiline-fields=true \
 --nodes "import/disease.nodes.Alias.csv" \
 --nodes "import/disease.nodes.Complication.csv" \
 --nodes "import/disease.nodes.Department.csv" \
 --nodes "import/disease.nodes.Disease.csv" \
 --nodes "import/disease.nodes.Drug.csv" \
 --nodes "import/disease.nodes.Part.csv" \
 --nodes "import/disease.nodes.Symptom.csv" \
 --relationships "import/disease.relationships.ALIAS_IS.csv" \
 --relationships "import/disease.relationships.DEPARTMENT_IS.csv" \
 --relationships "import/disease.relationships.HAS_COMPLICATION.csv" \
 --relationships "import/disease.relationships.HAS_DRUG.csv" \
 --relationships "import/disease.relationships.HAS_SYMPTOM.csv" \
 --relationships "import/disease.relationships.PART_IS.csv" \
 --force 
 
# 单行模式
docker exec -it neo4j bin/neo4j-admin import --multiline-fields=true      --nodes "import/disease.nodes.Alias.csv"     --nodes "import/disease.nodes.Complication.csv"     --nodes "import/disease.nodes.Department.csv"     --nodes "import/disease.nodes.Disease.csv"     --nodes "import/disease.nodes.Drug.csv"     --nodes "import/disease.nodes.Part.csv"     --nodes "import/disease.nodes.Symptom.csv"     --relationships "import/disease.relationships.ALIAS_IS.csv"     --relationships "import/disease.relationships.DEPARTMENT_IS.csv"     --relationships "import/disease.relationships.HAS_COMPLICATION.csv"     --relationships "import/disease.relationships.HAS_DRUG.csv"     --relationships "import/disease.relationships.HAS_SYMPTOM.csv"     --relationships "import/disease.relationships.PART_IS.csv"     --force

#导入成功之后要重启Neo4j
docker restart neo4j
~~~
