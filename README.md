SimpleRangeView
=======
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://api.bintray.com/packages/bendikv/my_libraries/simple-range-view/images/download.svg) ](https://bintray.com/bendikv/my_libraries/simple-range-view/_latestVersion)

SimpleRangeView is custom view component for Android, written in Kotlin, that provides for the selection of a range of discrete values designated by tick marks.

## Gradle

```groovy
dependencies {
	compile 'me.bendik.simplerangeview:simplerangeview:0.1.1'
}
```

Basic usage             |  RangeView Builder
:-------------------------:|:-------------------------:
![](https://github.com/bendikv/simple-range-view/blob/master/screenshots/basic_usage.png)  |  ![](https://github.com/bendikv/simple-range-view/blob/master/screenshots/builder.png)

Attributes
=======

Developers can customize the following attributes (both via XML and programmatically):

### Range Attributes
| name                        |  format   | description               | default value |
| :--------------------------:| :------:  | :-----------:             | :-----------: |
| lineColor            | color     | Range bar color          | #F7F7F7       |
| activeLineColor            | color     | Active range bar color          | #0C6CE1       |
| fixedLineColor            | color     | Fixed range bar color          | #E3E3E3       |
| lineThickness        | dimension   | Range bar thickness             | 4 |
| activeLineThickness        | dimension   | Active range bar thickness             | 6 |
| fixedLineThickness        | dimension   | Fixed range bar thickness             | 6 |
| innerRangePadding        | dimension   | Range inner padding             | 16 |
| innerRangePaddingLeft        | dimension   | Range inner left padding             | 16 |
| innerRangePaddingRight        | dimension   | Range inner right padding             | 16 |
| movable         | boolean   | It allows to move the entire active range             | false |
| showFixedLine         | boolean   | Show fixed range bar             | false |
| count        | integer   | Count of ticks             | 10 |
| start        | integer   | Start of active range             | 0 |
| end        | integer   | End of active range             | 9 |
| minDistance        | integer   | Minimal active range length             | 1 |
| startFixed        | integer   | Start of fixed range             | 0 |
| endFixed        | integer   | End of fixed range             | 0 |

### Tick Attributes
| name                        |  format   | description               | default value |
| :--------------------------:| :------:  | :-----------:             | :-----------: |
| tickColor            | color     | Range bar tick color          | #C5C5C5       |
| activeTickColor            | color     | Active tick color          | #FFFFFF       |
| fixedTickColor            | color     | Fixed tick color          | #C5C5C5       |
| tickRadius        | dimension   | Tick radius             | 1 |
| activeTickRadius        | dimension   | Active tick radius             | 1 |
| fixedTickRadius        | dimension   | Fixed tick radius             | 1 |
| showTicks         | boolean   | Show ticks             | true |
| showActiveTicks         | boolean   | Show fixed ticks            | true |
| showFixedTicks         | boolean   | Show active ticks            | true |

### Label Attributes
| name                        |  format   | description               | default value |
| :--------------------------:| :------:  | :-----------:             | :-----------: |
| labelColor            | color     | Label color          | #C5C5C5       |
| activeLabelColor            | color     | Active label color          | #0C6CE1       |
| fixedLabelColor            | color     | Fixed label color          | #0F7BFF       |
| activeThumbLabelColor            | color     | Active thumb label color          | #C5C5C5       |
| fixedThumbLabelColor            | color     | Fixed thumb label color          | #C5C5C5       |
| labelFontSize        | dimension   | Label font size             | 12 |
| labelMarginBottom        | dimension   | Label bottom margin             | 16 |
| showLabels         | boolean   | Show labels             | true |

### Thumb Attributes
| name                        |  format   | description               | default value |
| :--------------------------:| :------:  | :-----------:             | :-----------: |
| activeThumbColor            | color     | Active thumb color         | #0F7BFF       |
| activeFocusThumbColor            | color     | Focused thumb color          | #0F7BFF       |
| fixedThumbColor            | color     | Fixed thumb color          | #E3E3E3       |
| activeFocusThumbAlpha            | float     | Focused thumb alpha          | 1.0       |
| activeThumbRadius        | dimension   | Active thumb radius             | 10 |
| activeThumbFocusRadius        | dimension   | Focused thumb radius             | 14 |
| fixedThumbRadius        | dimension   | Fixed thumb radius             | 10 |

Examples
=======

## Layout XML

This is a SimpleRangeView with a lower and upper value, count of ticks and fixed range
```xml
<me.bendik.simplerangeview.SimpleRangeView
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/fixed_rangeview"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:count="10"
    app:start="3"
    app:end="6"
    app:showFixedLine="true"
    app:startFixed="2"
    app:endFixed="7"
    />
```

This is a SimpleRangeView with movable active range and minimal length of active range
```xml
<me.bendik.simplerangeview.SimpleRangeView
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/fixed_rangeview"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:count="10"
    app:start="3"
    app:end="6"
    app:movable="true"
    app:minDistance="2"
    />
```

## Adding listeners
```java
rangeView.setOnRangeLabelsListener(new SimpleRangeView.OnRangeLabelsListener() {
    @Nullable
    @Override
    public String getLabelTextForPosition(@NotNull SimpleRangeView rangeView, int pos, @NotNull SimpleRangeView.State state) {
        return String.valueOf("L" + (pos+1));
    }
});

rangeView.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
    @Override
    public void onStartRangeChanged(@NotNull SimpleRangeView rangeView, int start) {
        editStart.setText(String.valueOf(start));
    }

    @Override
    public void onEndRangeChanged(@NotNull SimpleRangeView rangeView, int end) {
        editEnd.setText(String.valueOf(end));
    }
});

rangeView.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener() {
    @Override
    public void onRangeChanged(@NotNull SimpleRangeView rangeView, int start, int end) {
        editRange.setText(String.valueOf(start) + " - " + String.valueOf(end));
    }
});
```
## Using Builder
```java
final SimpleRangeView rangeView = new SimpleRangeView.Builder(this)
        .count(10)
        .start(2)
        .end(7)
        .startFixed(1)
        .endFixed(8)
        .showFixedLine(true)
        .movable(true)
        .onChangeRangeListener(this)
        .onTrackRangeListener(this)
        .onRangeLabelsListener(this)
        .build();
```


Change Log
=======
```
0.1.1 - Bugfixes & Handle Instance State
0.1 - Initial release
```

TODO
=======
- [ ] Get default colors from material and appcompat themes
- [x] Handle onSaveInstanceState / onRestoreInstanceState
- [ ] Implement minimal distance between two active range labels (?)

License
=======
```
Copyright (C) 2016 Vitaliy Bendik

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
