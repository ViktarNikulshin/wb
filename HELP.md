Если запускать контейнер docker на windows нужно 
1. создать переменную среды  JAVA_TOOL_OPTIONS: -Dfile.encoding=UTF8
2. Поместить в папку Dockerfile
3. создать папку target
4. поместить туда war файл
5. создать папку tokens
6. поместить туда [StoredCredential](tokens%2FStoredCredential)
7. открыть терминал в docker-desktop 
8. перейти командой cd в папку с Dockerfile 
9. выполнить команду docker build . 
10. Затем запустить image в docker-desktop

# Для замены переменных нужно зайти в war по пути WEB-INF\classes\application.yml
