# Slidable

A lightweight Android library providing customizable **Before/After image comparison** views.

## Views

| View | Description |
|------|-------------|
| `BeforeAfterView` | Standard rectangular before/after slider |
| `CircularBeforeAfterView` | Circular-clipped before/after slider |

## Usage

### In XML

```xml
<com.knightfiury.slidable.BeforeAfterView
    android:layout_width="match_parent"
    android:layout_height="300dp"
    app:beforeImage="@drawable/before"
    app:afterImage="@drawable/after"
    app:dividerColor="#FFFFFF"
    app:dividerWidth="4dp"
    app:knobColor="#FFFFFF"
    app:knobRadius="20dp"
    app:initialPosition="0.5" />

<com.knightfiury.slidable.CircularBeforeAfterView
    android:layout_width="300dp"
    android:layout_height="300dp"
    app:beforeImage="@drawable/before"
    app:afterImage="@drawable/after"
    app:initialPosition="0.5" />
```

## Attributes

| Attribute | Format | Description |
|-----------|--------|-------------|
| `beforeImage` | reference | Drawable shown on the right (base layer) |
| `afterImage` | reference | Drawable shown on the left (overlay) |
| `dividerColor` | color | Color of the divider line |
| `dividerWidth` | dimension | Width of the divider line |
| `knobColor` | color | Color of the drag knob |
| `knobRadius` | dimension | Radius of the drag knob |
| `initialPosition` | float (0.0–1.0) | Starting position of the divider |

## Setup

Clone the repo and open in Android Studio. The `:app` module is a sample app demonstrating both views.

## License

MIT License
