# Hashery

This is a Java library that converts numbers into human-readable word combinations.

#### Get the library

- Download the latest release: https://github.com/DoSomething/hashery.android/releases
- Add the `hashery.jar` file to your project's libs folder

#### How to use it

- Create sets of words. These are not provided to you by the library.

```
String[] words0 = {"Tiny", "Medium", "Big"};
ArrayList<String> aWords0 = new ArrayList<String>(Arrays.asList(words0));

String[] words1 = {"Red", "Green", "Blue"};
ArrayList<String> aWords1 = new ArrayList<String>(Arrays.asList(words1));

String[] words2 = {"Chicken", "Goat", "Pig};
ArrayList<String> aWords2 = new ArrayList<String>(Arrays.asList(words2));

ArrayList<ArrayList<String>> allWords = new ArrayList<ArrayList<String>>();
allWords.add(aWords0);
allWords.add(aWords1);
allWords.add(aWords2);
```

- Instantiate a Hashery object.

```
Hashery hashery = new Hashery(allWords);
```

- Encode numbers into word combinations.

```
String encoded = hashery.encode(someNumber);
```

- Decode word combinations back into the original numbers.

```
int decoded = hashery.decode(encodedString);
```

---

There's also sample Android app [code](https://github.com/DoSomething/hashery.android/blob/master/app/src/main/java/org/dosomething/hashery/samples/MainActivity.java) included that can be helpful.

![hashery sample screenshot](https://raw.githubusercontent.com/DoSomething/hashery.android/master/hashery_example.png)

#### Limitations

- The library does not come with its own set of default words. They must be provided.
- The number of words in the encoded word combination is determined by the number of word sets provided.
  - For example, if `words` is an array of size 5, then `new Hashery(words);` will supply 5-word results.
- The length of the word sets supplied to Hashery must all be the same length.
- The maximum value that numbers can be is `L ^ S` where L is the length of a word set and S is the # of sets.
  - For example, for word sets of length 3. If 5 sets are provided, the max value that we can encode is `3 ^ 5 = 243`

