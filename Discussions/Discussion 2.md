# Discussion 2

## 1 - Pass by What?

<img src="Untitled.assets/image-20200618190347217.png" alt="image-20200618190347217" style="zoom:50%;" />

**What would Java display**?

"Name: Pikachu, Level: 100"

The assignment on line 19 rebinds the local variable `level` to a new value, it has no effect on the level of `poke` that was set above. Similarly, the instantiation on line 20 rebinds the local variable `poke` to a new Pokemon object.

## 2 - Static Methods and Variables

<img src="Discussion%202.assets/image-20200618190527515.png" alt="image-20200618190527515" style="zoom:50%;" />

Output:

```
Nyan! I'm Cream the cat!
Nyan! I'm Tubbs the cat!
<as above but with 'nyan!'>
```

Why? Because setting static fields and calling static methods using an instance of the class is treated the same as performing those actions on the class itself

