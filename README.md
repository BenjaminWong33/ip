# Benji project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Benji_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 25, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 25** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Benji.java` file, right-click it, and choose `Run Benji.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
     BBBBB   EEEEEEE  NN   NN  JJJJJJJ  IIIIIII
     BB  BB  EE       NNN  NN     JJJ     III
     BBBBB   EEEEE    NN N NN     JJJ     III
     BB  BB  EE       NN  NNN  JJ JJJ     III
     BBBBB   EEEEEEE  NN   NN   JJJJJ   IIIIIII
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

## Acknowledgements

### AI assistance

BenjaminWong33 used OpenAI Codex / ChatGPT (GPT-5.6 Terra) extensively as a
learning aid during development. It provided explanations and code suggestions
for selected Java, JavaFX, testing, documentation, and GitHub Actions CI work,
including parts of `MainWindow.java`, `DialogBox.java`, and
`MainWindow.fxml`. The author reviewed, adapted, integrated, and tested the
resulting changes.

### Reused and adapted work

- The initial JavaFX chatbot structure and `DialogBox` approach were adapted
  from the [SE-EDU JavaFX tutorial](https://se-education.org/guides/tutorials/javaFx.html).
  This is course material, for which acknowledgement is optional; it is included
  here for transparency.
- The robot avatar (`src/main/resources/images/benji.png`) is by
  [Vectorly on Canva](https://www.canva.com/graphics/MAG4Vg4a_rI-ai-robot/).
- The user avatar (`src/main/resources/images/user.png`) is from
  [PNGEgg](https://www.pngegg.com/en/png-nclaw).

The image assets are used subject to their respective source licences and terms
of use.
