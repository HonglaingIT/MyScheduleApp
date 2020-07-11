<?php

sleep(2);

$todo1 = new stdClass();
$todo1 ->id = 1;
$todo1 ->title = 'HW';
$todo1 ->description = 'Hello';
$todo1 ->deadline = 'Monday';

$todo2 = new stdClass();
$todo2->id = 2;
$todo2 ->title = 'HW1';
$todo2->description = 'Hi';
$todo2->deadline = 'Tuesday';

$todo3 = new stdClass();
$todo3->id = 3;
$todo3 ->title = 'HW2';
$todo3->description = 'hihi';
$todo3->deadline = 'wednesday';

$todos = array($todo1, $todo2, $todo3);

$json = json_encode($todos);

echo $json;