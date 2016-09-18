# SkinManager
Introduce several main Android application changing skin methods

## Demo Structure

#### SkinManager
SkinManager is an Android app which load resource and change skin:

(1) use android theme style

(2) use zip file, manual load and parse the skin resource 

(3) use apk file, install the apk with same signature and shareUserId

(4) use apk file, load the apk as a android plugin 


#### skin1
skin1 is a simple android application, has no java file, just resource, loaded by host as plugin   


Code in MainActivity.java

```java
switch (v.getId()) {
            case R.id.button:
                intent = new Intent(MainActivity.this, ThemeActivity.class);
                break;
            case R.id.button2:
                intent = new Intent(MainActivity.this, DynamicZipActivity.class);
                break;
            case R.id.button3:
                intent = new Intent(MainActivity.this, ApkShareIdActivity.class);
                break;
            case R.id.button4:
                intent = new Intent(MainActivity.this, ApkPluginActivity.class);
                break;
            default:
                break;
        }
```
