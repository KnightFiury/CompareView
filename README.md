# CompareView

[![](https://jitpack.io/v/KnightFiury/CompareView.svg)](https://jitpack.io/#KnightFiury/CompareView)
![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg)
![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)

A lightweight Android library for beautiful **before/after image comparison** views with a draggable divider. Supports both rectangular and circular variants.

---

## Preview

| BeforeAfterView | CircularBeforeAfterView |
|:-:|:-:|
| Rectangular slider | Circular clipped slider |

---

## Setup

### Step 1 — Add JitPack to your `settings.gradle`

```gradle
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2 — Add the dependency

```gradle
dependencies {
    implementation 'com.github.KnightFiury:CompareView:v1.0.6'
}
```

---

## Usage

### In XML

```xml
<!-- Rectangular -->
<com.knightfiury.slidable.BeforeAfterView
    android:id="@+id/beforeAfterView"
    android:layout_width="match_parent"
    android:layout_height="300dp"
    app:beforeImage="@drawable/before"
    app:afterImage="@drawable/after"
    app:dividerColor="#FFFFFF"
    app:dividerWidth="4dp"
    app:knobColor="#FFFFFF"
    app:knobRadius="20dp"
    app:initialPosition="0.5" />

<!-- Circular -->
<com.knightfiury.slidable.CircularBeforeAfterView
    android:id="@+id/circularView"
    android:layout_width="300dp"
    android:layout_height="300dp"
    app:beforeImage="@drawable/before"
    app:afterImage="@drawable/after"
    app:dividerColor="#FFFFFF"
    app:dividerWidth="4dp"
    app:knobColor="#FFFFFF"
    app:knobRadius="20dp"
    app:initialPosition="0.5" />
```

### In Java

```java
BeforeAfterView view = findViewById(R.id.beforeAfterView);

// Set images programmatically
view.setBeforeImage(BitmapFactory.decodeResource(getResources(), R.drawable.before));
view.setAfterImage(BitmapFactory.decodeResource(getResources(), R.drawable.after));

// Move the divider programmatically
// move(int direction, float amount)
// direction: 1 = right, -1 = left
// amount: 0.0f to 1.0f
view.move(1, 0.2f);   // move right by 20%
view.move(-1, 0.2f);  // move left by 20%
```

---

## XML Attributes

| Attribute | Format | Description |
|-----------|--------|-------------|
| `beforeImage` | reference | The "before" image (base layer) |
| `afterImage` | reference | The "after" image (overlay) |
| `dividerColor` | color | Color of the divider line |
| `dividerWidth` | dimension | Stroke width of the divider line |
| `knobColor` | color | Color of the drag knob |
| `knobRadius` | dimension | Radius of the drag knob |
| `initialPosition` | float `0.0–1.0` | Starting position of the divider |

---

## Java API

| Method | Description |
|--------|-------------|
| `setBeforeImage(Bitmap)` | Set the before image at runtime |
| `setAfterImage(Bitmap)` | Set the after image at runtime |
| `move(int direction, float amount)` | Move the divider — `1` for right, `-1` for left |

---

## License

```
Copyright (c) 2026 KnightFiury

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
