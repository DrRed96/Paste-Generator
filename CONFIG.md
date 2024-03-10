# Configuration
Config files are JSON files that contain information for the paste.<br>
Config files must contain the properties `title` and `author`, and can also optionally contain `width` (default 150) and `margin` (default 20).<br>
Config files should also contain `components` which will form the body of the paste.<br>
<br>
**Example config file:**
```json
{
  "title": "Test paste",
  "author": "probably not kt",
  "width": 120,
  "margin": 0,
  "components": [
    {
      "type": "section_header",
      "section": 0,
      "sub_section": 0,
      "title": "Test"
    }
  ]
}
```

## Components
Components are specified in the components array as shown in the example config file. All the properties of the component must be defined in the object for that component. A `?` after the type name indicates that the property is optional.<br>
**Note:** more of these will probably be added, if there's something in here that you want you can message be on Discord (`@i_dont_exist_lol`) or Telegram (`i_do_not_exist_lol`) and in the meantime just add it in manually.

### `art`
Adds ASCII art (or some other text) from another file to the paste

| Property    | Type   | Description                               |
|-------------|--------|-------------------------------------------|
| `path`      | String | Path to the file containing the ascii art |
| `alignment` | Enum?  | Alignment of the image (`LEFT`, `CENTER`) |
**Example**
```json
{
  "type": "art",
  "path": "art.txt",
  "alignment": "LEFT"
}
```

### `indexed_value_list`
Adds a named value list with an index to the paste

| Property | Type     | Description            |
|----------|----------|------------------------|
| `name`   | String   | Name of the value list |
| `values` | Object[] | The values             |
| `index`  | Number   | Index of the list      |
**Example**
```json
{
  "type": "indexed_value_list",
  "index": 0,
  "name": "Stuff",
  "values": [
    {
      "name": "hello",
      "value": "goodbye"
    },
    {
      "name": "lol",
      "value": "lmao"
    }
  ]
}
```

### `list`
Adds a list to the paste

| Property | Type     | Description        |
|----------|----------|--------------------|
| `name`   | String   | Name of the list   |
| `values` | String[] | Values in the list |
**Example**
```json
{
  "type": "list",
  "name": "A List",
  "values": [
    "one fish",
    "two fish",
    "red fish",
    "blue fish"
  ]
}
```

### `new_line`
Adds new lines to the paste

| Property | Type    | Description                |
|----------|---------|----------------------------|
| `amount` | Number? | Amount of new lines to add |
**Example**
```json
{
  "type": "new_line",
  "amount": 1
}
```

### `paragraph`
Adds a paragraph of text to the paste

| Property    | Type   | Description                                                   |
|-------------|--------|---------------------------------------------------------------|
| `text`      | String | Text in the paragraph                                         |
| `alignment` | Enum?  | Alignment of the paragraph (`LEFT`, `CENTER`, `CENTER_SMALL`) |
**Example**
```json
{
  "type": "paragraph",
  "text": "The quick brown fox jumps over the lazy dog",
  "alignment": "LEFT"
}
```

### `section_header`
Adds a header to display a new section to the paste

| Property      | Type   | Description          |
|---------------|--------|----------------------|
| `section`     | Number | Section Number       |
| `sub_section` | Number | Sub Section Number   |
| `title`       | String | Title of the section |
**Example**
```json
{
  "type": "section_header",
  "section": 1,
  "sub_section": 1,
  "title": "Basic Information"
}
```

### `value`
Adds a named value to the paste, this will not automatically insert a new line after it

| Property | Type   | Description       |
|----------|--------|-------------------|
| `name`   | String | Name of the value |
| `value`  | String | The value itself  |
**Example**
```json
{
  "type": "value",
  "name": "Full Name",
  "value": "Not Donald Trump"
}
```

### `value_list`
Adds a named value list to the paste

| Property | Type     | Description            |
|----------|----------|------------------------|
| `name`   | String   | Name of the value list |
| `values` | Object[] | The values             |
**Example**
```json
{
  "type": "indexed_value_list",
  "name": "Stuff",
  "values": [
    {
      "name": "hello",
      "value": "goodbye"
    },
    {
      "name": "lol",
      "value": "lmao"
    }
  ]
}
```