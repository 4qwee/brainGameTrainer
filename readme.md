# Hello, World!

Это простенькая брейн-система предназначена для тренеровок. Нет, конечно же никто не запрещает вам использовать её для других целей, но в этом случае я умываю руки.
***

## Минимальные системные требования:
* Компьютер моложе меня с установленной [jre1.6](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Две мышки
* Три человека

## Установка
* Прежде всего нужно скачать последнюю версию [программы](https://github.com/downloads/4qwee/brainGameTrainer/brainGameTrainer.jar)
* Если у вас нет локальной MySQL базы с вопросами или вы не знаете, что это такое, то вам нужно скачать [это](https://github.com/downloads/4qwee/brainGameTrainer/main.db) и положить в папку с программой.
* Если вы не знаете как запустить jar-файл, то вам нужно скачать [это](https://github.com/downloads/4qwee/brainGameTrainer/BrainGameTrainer.bat) и опять же положить в папку с программой. Чтобы запустить программу, просто запустите этот файл.

## Принцип работы
Ведущему для управления программой достаточно трёх кнопок: "пробел", "влево" и "вправо". По нажатию на "пробел" вопросы загружаются из базы, время начинает тикать, да и вообще - мир становится чуточку лучше. Нажатиями кнопок "вправо" и "влево" ведущий оценивает правильность ответа команды. Как несложно догадаться "влево" - это значит "да", а "влево" - "никак нет".

Команды вообще обходятся одной кнопкой мыши. Правой или левой. Как гуманист, я верю в честность людей, а как ленивая скотина, не хочу маяться с WinAPI.

Если вы смогли разобраться в предыдущих абзацах и вообще уверенный пользователь ЭВМ, то можете полазить в настройках и открыть для себя, что командам можно давать имена, вести игру определённое количество раундов и брать вопросы не только из локальной базы.