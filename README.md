# dropboxreporter
[![Build Status](https://qbic-intranet.am10.uni-tuebingen.de/jenkins/buildStatus/icon?job=dropboxreporter&build=4)](https://qbic-intranet.am10.uni-tuebingen.de/jenkins/job/dropboxreporter/4/) [![codecov](https://codecov.io/gh/qbicsoftware/dropboxreporter/branch/development/graph/badge.svg)](https://codecov.io/gh/qbicsoftware/dropboxreporter) [![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0)

Reports stuff that happened on a particular day, based on the logs of the dropboxhandler.

Developer: Sven Fillinger<br>
E-mail: sven.fillinger@qbic.uni-tuebingen.de<br>
Snapshots: [v1.0](https://peltzer.com.de/nexus/content/repositories/snapshots/life/qbic/dropboxreporter/1.0-SNAPSHOT/)<br>
Releases: n.A.

## How to use it
Currently, the dropboxreporter only summarizes log events, given a certain date in format **'YYYY-MM-DD'**.

### Commandline interface
The dropboxreporter can be executed, after compiling the the source code. You can then summarize a log file by calling:

    > java -jar dropboxhandler.jar [date] [/path/to/logfile.log]

Be aware that the **date** must have the **format** 'YYYY-MM-DD'.

### Example output
If the parsing was successful, you will see an output similar to this:

    Log file parsed successfully.
    -------------------
    Log overview
    Date: 2017-03-20
    Log: /home/sven/dropboxhandler.log
    -------------------
    Project code: QSGPR

    numberOfManualIntervention: 0
    numberOfIncomingFiles: 2

    -------------------
    Files without barcode: 2
    Filepath:	/mnt/nfs/qbic/dropboxes/manual/qeana07-pct/20170320131133_20140523_CO_0480SiDG_R02_OG_04.raw
    Filepath:	/mnt/nfs/qbic/dropboxes/manual/qeana07-pct/20170320131932_20140523_CO_0480SiDG_R02_OG_03.raw
    
The lines 

    Log overview
    Date: 2017-03-20
    Log: /home/sven/dropboxhandler.log
    
show again the input parameters. ```Date``` is the date you wanted the summary for. ```Log``` respectively shows the path to the log file you wanted to be searched.

The line

     Project code: QSGPR

obviously reports a **summary** for a found **project code**. All files with barcodes belonging to this particular project will be summarized as

    numberOfManualIntervention: 0
    numberOfIncomingFiles: 2
    
meaning the number files for the project that still need **manual intervention** (e.g. the target dropbox was wrong or there was no matching rule defined in the dropboxhandler config) and the **total
number** of incoming files for that project are displayed.

In case of incoming files that need manual intervention (because they don't carry a valid barcode or just have none), it will be summarized
like in this example:

    Files without barcode: 2
    Filepath:	/mnt/nfs/qbic/dropboxes/manual/qeana07-pct/20170320131133_20140523_CO_0480SiDG_R02_OG_04.raw
    Filepath:	/mnt/nfs/qbic/dropboxes/manual/qeana07-pct/20170320131932_20140523_CO_0480SiDG_R02_OG_03.raw
 
