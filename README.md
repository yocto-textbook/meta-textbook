# Quick Start
* The following commands demonstrate how to set up a new Yocto workspace,
* initialize the manifest repository, synchronize all layers, configure
* the build environment, and build/run a reference image.
```
$ source envsetup.sh                                                # Set up the Yocto build environment
$ bitbake textbook-core-image                                       # Build the target
$ runqemu textbook-core-image nographic                             # Run the image in QEMU (no graphics)
```
