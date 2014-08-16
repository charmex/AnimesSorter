AnimesSorter
============

This program will look for files in the selected folder and sort them in their respective folder.

Example: 

If theres a folder with the following files:
  [HorribleSubs] Rokujouma no Shinryakusha - 05 [720p].mkv
  [HorribleSubs] Sword Art Online II - 05 [720p].mkv
  [HorribleSubs] Bakumatsu Rock - 06 [720p].mkv
  [HorribleSubs] Akame ga Kill! - 03 [720p].mkv

It will create the following folders:
  Rokujouma no Shinryakusha
  Sword Art Online II
  Bakumatsu Rock
  Akame ga Kill!
  
and then it will move the files to their respective folder

  Rokujouma no Shinryakusha
    -[HorribleSubs] Rokujouma no Shinryakusha - 05 [720p].mkv
  Sword Art Online II
    -[HorribleSubs] Sword Art Online II - 05 [720p].mkv
  Bakumatsu Rock
    -[HorribleSubs] Bakumatsu Rock - 06 [720p].mkv
  Akame ga Kill!
    -[HorribleSubs] Akame ga Kill! - 03 [720p].mkv
    
How does it do it:
The program does a series of string manipulation to find the show name.
1 - It removes whatever is inside of "[]".
2 - It removes everything after "-"
Whatever is left is considered the show name.

Known problems:
If the file name has no spaces it will be considered an invalid file and it'll be ignored.

This was made as a just for fun project.
