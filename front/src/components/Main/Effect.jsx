import * as React from 'react';

import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import Typography from './Typography';
import productCurvyLines from '../../assets/images/productCurvyLines.png';
import effect1 from '../../assets/images/effect1.svg';
import effect2 from '../../assets/images/effect2.svg';
import effect3 from '../../assets/images/effect3.svg';
import {
    fontNormal, 
    fontBold,
    item,
    content,
} from './Main.style';

export default function Effect() {
    return (
        <Box
            style={{ backgroundColor : '#ebfceb' }}
            // component="section"
            sx={{
                display: 'flex',
                overflow: 'hidden',
            }}>
            <Container
                sx={{
                    mt: 15,
                    mb: 20,
                    position: 'relative',
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center'
                }}>
                <Box
                    component="img"
                    src={productCurvyLines}
                    alt="curvy lines"
                    sx={{
                        pointerEvents: 'none',
                        position: 'absolute',
                        top: -180,
                        opacity: 0.7
                    }}/>
                <Typography
                    variant="h4"
                    marked="center"
                    component="h2"
                    style={ fontBold }
                    sx={{
                        mb: 14
                    }}>
                    What We Expect
                </Typography>
                <div>
                <Grid container spacing={5}>
                    <Grid item xs={12} md={4}>
                        <Box sx={item}>
                            <Box
                                component="img"
                                src={effect1}
                                alt="suitcase"
                                sx={{
                                    height: 55
                                }}/>
                            <Typography
                                variant="h6"
                                style={ fontBold }
                                sx={{
                                    my: 5
                                }}>
                                ??????<br/>(Virus-Free)
                            </Typography>
                            <Typography variant="h5" style={ fontNormal }>
                                ????????? ????????? ???????????? ????????? 19????????? ???????????? ????????? ??? ??? ????????????.
                            </Typography>
                        </Box>
                    </Grid>
                    <Grid item xs={12} md={4}>
                        <Box sx={item}>
                            <Box
                                component="img"
                                src={effect2}
                                alt="graph"
                                sx={{
                                    height: 55
                                }}/>
                            <Typography
                                variant="h6"
                                style={ fontBold }
                                sx={{
                                    my: 5
                                }}>
                                ?????? ??????<br/>(Exercise Assistant)
                            </Typography>
                            <Typography variant="h5" style={ fontNormal }>
                                ???????????? ?????? ????????? ???????????? AI??? ?????? ????????? ???????????? ???????????? ????????? ???????????? ???????????? ????????? ??? ????????????.
                            </Typography>
                        </Box>
                    </Grid>
                    <Grid item xs={12} md={4}>
                        <Box sx={item}>
                            <Box
                                component="img"
                                src={effect3}
                                alt="clock"
                                sx={{
                                    height: 55
                                }}/>
                            <Typography
                                variant="h6"
                                style={ fontBold }
                                sx={{
                                    my: 5
                                }}>
                                ??????<br/>(Health)
                            </Typography>
                            <Typography 
                                variant="h5" 
                                style={ content }>
                                ???????????? ????????? ???????????? ?????? ??????????????? ???????????? ????????? ???????????? ????????? ?????? ??? ????????????.
                            </Typography>
                        </Box>
                    </Grid>
                </Grid>
                </div>
            </Container>
        </Box>
    );
}

