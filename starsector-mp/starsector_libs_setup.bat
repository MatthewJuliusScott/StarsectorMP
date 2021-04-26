CALL mvn install:install-file -Dfile="./lib/starfarer.api.jar" -DgroupId=com.fs.starfarer.api -DartifactId=starfarer.api -Dversion=0.9.5a-RC15 -Dpackaging=jar -Dsources="./lib/starfarer.api.zip" -DgeneratePom=true

CALL mvn install:install-file -Dfile="./lib/starfarer_obf.jar" -DgroupId=com.fs.starfarer -DartifactId=starfarer -Dversion=0.9.5a-RC15 -Dpackaging=jar -DgeneratePom=true

CALL mvn install:install-file -Dfile="./lib/fs.common_obf.jar" -DgroupId=com.fs -DartifactId=common -Dversion=0.9.5a-RC15 -Dpackaging=jar -DgeneratePom=true

CALL mvn install:install-file -Dfile="./lib/fs.sound_obf.jar" -DgroupId=com.fs -DartifactId=sound_obj -Dversion=0.9.5a-RC15 -Dpackaging=jar -DgeneratePom=true

CALL mvn install:install-file -Dfile="./lib/LazyLib.jar" -DgroupId=org.lazywizard -DartifactId=lazylib -Dversion=2.6 -Dpackaging=jar -DgeneratePom=true

CALL mvn install:install-file -Dfile="./lib/lwjgl.jar" -DgroupId=org.lwjgl -DartifactId=lwjgl -Dversion=windows-native -Dpackaging=jar -DgeneratePom=true

CALL mvn install:install-file -Dfile="./lib/lwjgl_util.jar" -DgroupId=org.lwjgl -DartifactId=lwjgl_util -Dversion=windows-native -Dpackaging=jar -DgeneratePom=true