@echo off
@REM merges changes from branch lesson-100 to all higher lessons until a merge conflict is discovered

set SOURCE=lesson-100
set TARGET=lesson-110

echo %TARGET%

git checkout %TARGET%
git merge --no-ff %SOURCE%

if errorlevel 1 (
    echo Merge of %SOURCE% into %TARGET% did not return exit code 0 -- aborting now..
    exit /b
)

set SOURCE=%TARGET%
set TARGET=lesson-120

git checkout %TARGET%
git merge --no-ff %SOURCE%

if errorlevel 1 (
    echo Merge of %SOURCE% into %TARGET% did not return exit code 0 -- aborting now..
    exit /b
)


set SOURCE=%TARGET%
set TARGET=lesson-130

git checkout %TARGET%
git merge --no-ff %SOURCE%

if errorlevel 1 (
    echo Merge of %SOURCE% into %TARGET% did not return exit code 0 -- aborting now..
    exit /b
)


set SOURCE=%TARGET%
set TARGET=lesson-150

git checkout %TARGET%
git merge --no-ff %SOURCE%

if errorlevel 1 (
    echo Merge of %SOURCE% into %TARGET% did not return exit code 0 -- aborting now..
    exit /b
)