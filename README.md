## Package Command Line App

This application require JRE (Java Runtime Environment) to successfully running on your device.

In case you want to copy and paste some files from one based source code and unfortunately each file has different directory, when u use manual way, you will first create directories first then copy paste the files, it will not be problem when the files is not many, but it will be wasted your time if the files is many and the folder is varios too!

In this application you can list the filepath into .txt files then when you run the command, it will be create directory from the filepath automatically and then starting to copy the intended files, also the application will be zip the final result.
### How To Run

1.  Go to the application folder to get the diff.txt and jar files

2. Open the terminal based on this application folder

3. Run The Command :

    

>  java -jar delivery-sc-worker-1.0.0.jar package ./diff.txt
> -dst--folder --src--folder


Example : 
> java -jar delivery-sc-worker-1.0.0.jar package ./diff.txt
> ./20231008/sipegawai/ E:/PERSONAL/LARAVEL/SIPEGAWAI/

reading ./diff.text -> creating a folder in **dst folder** -> copy files from **src folder** -> paste in **dst folder** same as written filepath in ./diff.text -> zip the folder