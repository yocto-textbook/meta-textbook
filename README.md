# Quick Start
* The following commands demonstrate how to set up a new Yocto workspace,
* initialize the manifest repository, synchronize all layers, configure
* the build environment, and build/run a reference image.
```
$ source envsetup.sh                                                # Set up the Yocto build environment
$ bitbake core-image-minimal                                        # Build the core-image target
$ runqemu core-image-minimal nographic                              # Run the image in QEMU (no graphics)
```
